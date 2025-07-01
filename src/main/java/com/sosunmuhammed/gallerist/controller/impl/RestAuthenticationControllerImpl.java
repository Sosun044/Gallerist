package com.sosunmuhammed.gallerist.controller.impl;

import com.sosunmuhammed.gallerist.controller.IRestAuthenticationController;
import com.sosunmuhammed.gallerist.controller.RestBaseController;
import com.sosunmuhammed.gallerist.controller.RootEntity;
import com.sosunmuhammed.gallerist.dto.AuthRequest;
import com.sosunmuhammed.gallerist.dto.AuthResponse;
import com.sosunmuhammed.gallerist.dto.DtoUser;
import com.sosunmuhammed.gallerist.dto.RefreshTokenRequest;
import com.sosunmuhammed.gallerist.service.IAuthenticateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

    @Autowired
    private IAuthenticateService authenticateService;

    @PostMapping("/register")
    @Override
    public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest request) {
        return ok(authenticateService.register(request));
    }

    @PostMapping("/authenticate")
    @Override
    public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest authRequest) {

        return ok(authenticateService.authenticate(authRequest));
    }

    @PostMapping("/refreshToken")
    @Override
    public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        return ok(authenticateService.refreshToken(request));
    }
}
