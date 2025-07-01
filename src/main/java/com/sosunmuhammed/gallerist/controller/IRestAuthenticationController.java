package com.sosunmuhammed.gallerist.controller;

import com.sosunmuhammed.gallerist.dto.AuthRequest;
import com.sosunmuhammed.gallerist.dto.AuthResponse;
import com.sosunmuhammed.gallerist.dto.DtoUser;
import com.sosunmuhammed.gallerist.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

    public RootEntity<DtoUser> register(AuthRequest request);

    public RootEntity<AuthResponse> authenticate(AuthRequest authRequest);

    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest request);
}
