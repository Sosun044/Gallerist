package com.sosunmuhammed.gallerist.controller;

import com.sosunmuhammed.gallerist.dto.DtoAccount;
import com.sosunmuhammed.gallerist.dto.DtoAccountIU;
import com.sosunmuhammed.gallerist.dto.DtoCustomer;

import java.util.List;

public interface IRestAccountController {
    RootEntity<DtoAccount> save(DtoAccountIU dtoAccountIU);
    RootEntity<List<DtoAccount>> getAll(DtoAccount dtoAccount);
    RootEntity<DtoAccount> findAccount(Long id);
    RootEntity<String> deleteAccount(Long id);
    RootEntity<DtoAccount> updateAccount(Long id,DtoAccountIU dtoAccountIU);

}
