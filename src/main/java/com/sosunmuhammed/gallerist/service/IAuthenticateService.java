package com.sosunmuhammed.gallerist.service;

import com.sosunmuhammed.gallerist.dto.AuthRequest;
import com.sosunmuhammed.gallerist.dto.AuthResponse;
import com.sosunmuhammed.gallerist.dto.DtoUser;
import com.sosunmuhammed.gallerist.dto.RefreshTokenRequest;
import com.sosunmuhammed.gallerist.model.User;

public interface IAuthenticateService {

    public DtoUser register(AuthRequest authRequest);
    public AuthResponse authenticate(AuthRequest authRequest);
    public AuthResponse refreshToken(RefreshTokenRequest request);
}
