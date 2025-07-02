package com.sosunmuhammed.gallerist.mapper;

import com.sosunmuhammed.gallerist.model.RefreshToken;
import com.sosunmuhammed.gallerist.model.User;

import java.util.Date;
import java.util.UUID;

public class RefreshTokenMapper {
    public static RefreshToken generate(User user){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setCreateTime(new Date());
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000*60*60*4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        return refreshToken;
    }

}
