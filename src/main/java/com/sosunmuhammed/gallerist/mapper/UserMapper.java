package com.sosunmuhammed.gallerist.mapper;

import com.sosunmuhammed.gallerist.dto.AuthRequest;
import com.sosunmuhammed.gallerist.dto.DtoUser;
import com.sosunmuhammed.gallerist.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

public class UserMapper {

    public static User toEntity(AuthRequest request, PasswordEncoder encoder){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setCreateTime(new Date());
        return user;
    }
    public static DtoUser toDto(User user){
        DtoUser dtoUser = new DtoUser();
        dtoUser.setId(user.getId());
        dtoUser.setUsername(user.getUsername());
        dtoUser.setCreateTime(user.getCreateTime());
        return dtoUser;
    }
}
