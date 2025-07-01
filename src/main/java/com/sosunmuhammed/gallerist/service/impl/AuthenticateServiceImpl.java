package com.sosunmuhammed.gallerist.service.impl;

import com.sosunmuhammed.gallerist.dto.AuthRequest;
import com.sosunmuhammed.gallerist.dto.AuthResponse;
import com.sosunmuhammed.gallerist.dto.DtoUser;
import com.sosunmuhammed.gallerist.dto.RefreshTokenRequest;
import com.sosunmuhammed.gallerist.exception.BaseException;
import com.sosunmuhammed.gallerist.exception.ErrorMessage;
import com.sosunmuhammed.gallerist.exception.MessageType;
import com.sosunmuhammed.gallerist.jwt.JWTService;
import com.sosunmuhammed.gallerist.model.RefreshToken;
import com.sosunmuhammed.gallerist.model.User;
import com.sosunmuhammed.gallerist.repository.RefresTokenRepository;
import com.sosunmuhammed.gallerist.repository.UserRepository;
import com.sosunmuhammed.gallerist.service.IAuthenticateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticateServiceImpl implements IAuthenticateService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private RefresTokenRepository refresTokenRepository;

    private User createUser(AuthRequest authRequest){
        User user = new User();
        user.setCreateTime(new Date());
        user.setUsername(authRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(authRequest.getPassword()));

        return user;
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
        DtoUser dtoUser = new DtoUser();
        User savedUser = userRepository.save(createUser(authRequest));
        BeanUtils.copyProperties(savedUser,dtoUser);
        return dtoUser;
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {

        try {
            UsernamePasswordAuthenticationToken authenticationToken = new
                    UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword());
            authenticationProvider.authenticate(authenticationToken);

            Optional<User> optUser = userRepository.findByUsername(authRequest.getUsername());

            String accessToken = jwtService.generateToken(optUser.get());

            RefreshToken savedRefreshToken =  refresTokenRepository.save(createRefreshToken(optUser.get()));

            return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());

        }catch (Exception e){
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID,e.getMessage()));
        }

    }
    public boolean isValidRefreshToken(Date expiredDate){
        return new Date().before(expiredDate);
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        Optional<RefreshToken> optRefreshToken =  refresTokenRepository.findByRefreshToken(request.getRefreshToken());
        if (optRefreshToken.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND,request.getRefreshToken()));
        }
        if (!isValidRefreshToken(optRefreshToken.get().getExpiredDate())){
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED,request.getRefreshToken()));
        }
        User user = optRefreshToken.get().getUser();
        String accessToken =  jwtService.generateToken(user);
        RefreshToken savedRefreshToken =  refresTokenRepository.save(createRefreshToken(user));

        return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());
    }
}
