package com.sosunmuhammed.gallerist.mapper;

import com.sosunmuhammed.gallerist.dto.DtoAccount;
import com.sosunmuhammed.gallerist.dto.DtoAccountIU;
import com.sosunmuhammed.gallerist.model.Account;

import java.util.Date;

public class AccountMapper {
    public static Account toEntity(DtoAccountIU dto){
        Account account = new Account();
        account.setAccountNo(dto.getAccountNo());
        account.setIban(dto.getIban());
        account.setAmount(dto.getAmount());
        account.setCurrencyType(dto.getCurrencyType());
        account.setCreateTime(new Date());
        return account;
    }
    public static void updateEntity(Account account,DtoAccountIU dto){
        account.setAccountNo(dto.getAccountNo());
        account.setIban(dto.getIban());
        account.setAmount(dto.getAmount());
        account.setCurrencyType(dto.getCurrencyType());
    }
    public static DtoAccount toDto(Account account){
        DtoAccount dto = new DtoAccount();
        dto.setId(account.getId());
        dto.setAccountNo(account.getAccountNo());
        dto.setIban(account.getIban());
        dto.setAmount(account.getAmount());
        dto.setCurrencyType(account.getCurrencyType());
        dto.setCreateTime(account.getCreateTime());
        return dto;
    }
}
