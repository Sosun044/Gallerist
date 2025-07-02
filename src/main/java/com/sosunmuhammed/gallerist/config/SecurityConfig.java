package com.sosunmuhammed.gallerist.config;

import com.sosunmuhammed.gallerist.handler.AuthEntryPoint;
import com.sosunmuhammed.gallerist.jwt.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String REGISTER = "/register";
    public static final String AUTHENTICATE = "/authenticate";
    public static final String REFRESH_TOKEN = "/refreshToken";


    private final AuthenticationProvider authenticationProvider;
    private final JWTAuthenticationFilter authenticationFilter;
    private final AuthEntryPoint authEntryPoint;

    public SecurityConfig(AuthenticationProvider authenticationProvider, JWTAuthenticationFilter authenticationFilter, AuthEntryPoint authEntryPoint) {
        this.authenticationProvider = authenticationProvider;
        this.authenticationFilter = authenticationFilter;
        this.authEntryPoint = authEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->auth
                        .requestMatchers(AUTHENTICATE,REGISTER,REFRESH_TOKEN).permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(excepiton->excepiton.authenticationEntryPoint(authEntryPoint))
                .sessionManagement(session ->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter,UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }




}
