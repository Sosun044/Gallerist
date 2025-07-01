package com.sosunmuhammed.gallerist.service;


import com.sosunmuhammed.gallerist.dto.DtoAccount;
import com.sosunmuhammed.gallerist.dto.DtoAccountIU;

import java.util.List;

public interface IAccountService {
    DtoAccount save(DtoAccountIU dtoAccountIU);
    List<DtoAccount> getAll();
    DtoAccount findAccount(Long id);
    void deleteAccount(Long id);
    DtoAccount update(Long id,DtoAccountIU dtoAccountIU);
}
