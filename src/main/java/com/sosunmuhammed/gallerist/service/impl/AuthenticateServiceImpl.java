package com.sosunmuhammed.gallerist.service.impl;

import com.sosunmuhammed.gallerist.dto.AuthRequest;
import com.sosunmuhammed.gallerist.dto.AuthResponse;
import com.sosunmuhammed.gallerist.dto.DtoUser;
import com.sosunmuhammed.gallerist.dto.RefreshTokenRequest;
import com.sosunmuhammed.gallerist.exception.BaseException;
import com.sosunmuhammed.gallerist.exception.ErrorMessage;
import com.sosunmuhammed.gallerist.exception.MessageType;
import com.sosunmuhammed.gallerist.jwt.JWTService;
import com.sosunmuhammed.gallerist.mapper.RefreshTokenMapper;
import com.sosunmuhammed.gallerist.mapper.UserMapper;
import com.sosunmuhammed.gallerist.model.RefreshToken;
import com.sosunmuhammed.gallerist.model.User;
import com.sosunmuhammed.gallerist.repository.RefresTokenRepository;
import com.sosunmuhammed.gallerist.repository.UserRepository;
import com.sosunmuhammed.gallerist.service.IAuthenticateService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class AuthenticateServiceImpl implements IAuthenticateService {


    private final UserRepository userRepository;


    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    private final AuthenticationProvider authenticationProvider;

    private final JWTService jwtService;


    private final RefresTokenRepository refresTokenRepository;

    public AuthenticateServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationProvider authenticationProvider, JWTService jwtService, RefresTokenRepository refresTokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationProvider = authenticationProvider;
        this.jwtService = jwtService;
        this.refresTokenRepository = refresTokenRepository;
    }
    private RefreshToken createRefreshToken(User user){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreateTime(new Date());
        refreshToken.setUser(user);
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000*60*60*4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());

        return refreshToken;
    }

    @Override
    public DtoUser register(AuthRequest authRequest) {
        User savedUser = userRepository.save(UserMapper.toEntity(authRequest, bCryptPasswordEncoder));
        return UserMapper.toDto(savedUser);
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        try {
            authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            User user = userRepository.findByUsername(authRequest.getUsername())
                    .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.USER_NOT_FOUND, authRequest.getUsername())));

            String accessToken = jwtService.generateToken(user);
            RefreshToken refreshToken = refresTokenRepository.save(RefreshTokenMapper.generate(user));

            return new AuthResponse(accessToken, refreshToken.getRefreshToken());

        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
        }
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        RefreshToken refreshToken = refresTokenRepository.findByRefreshToken(request.getRefreshToken())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, request.getRefreshToken())));

        if (!isValid(refreshToken.getExpiredDate())) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, request.getRefreshToken()));
        }

        User user = refreshToken.getUser();
        String accessToken = jwtService.generateToken(user);
        RefreshToken newRefresh = refresTokenRepository.save(RefreshTokenMapper.generate(user));

        return new AuthResponse(accessToken, newRefresh.getRefreshToken());
    }
    private boolean isValid(Date expiredDate){
        return new Date().before(expiredDate);
    }

}
