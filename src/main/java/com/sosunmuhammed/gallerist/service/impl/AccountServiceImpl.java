package com.sosunmuhammed.gallerist.service.impl;

import com.sosunmuhammed.gallerist.dto.DtoAccount;
import com.sosunmuhammed.gallerist.dto.DtoAccountIU;
import com.sosunmuhammed.gallerist.exception.BaseException;
import com.sosunmuhammed.gallerist.exception.ErrorMessage;
import com.sosunmuhammed.gallerist.exception.MessageType;
import com.sosunmuhammed.gallerist.mapper.AccountMapper;
import com.sosunmuhammed.gallerist.model.Account;
import com.sosunmuhammed.gallerist.repository.AccountRepository;
import com.sosunmuhammed.gallerist.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements IAccountService {


    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public DtoAccount save(DtoAccountIU dtoAccountIU) {
        Account savedAccount = accountRepository.save(AccountMapper.toEntity(dtoAccountIU));
        return AccountMapper.toDto(savedAccount);
    }

    @Override
    public List<DtoAccount> getAll() {

        return accountRepository.findAll().stream()
                .map(AccountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DtoAccount findAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() ->new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString())));
        return AccountMapper.toDto(account);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).
                orElseThrow(() ->new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString())));
        accountRepository.delete(account);
    }

    @Override
    public DtoAccount update(Long id,DtoAccountIU dtoAccountIU) {
        Account account = accountRepository.findById(id).
                orElseThrow(() ->new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString())));
        AccountMapper.updateEntity(account,dtoAccountIU);
        Account updated = accountRepository.save(account);
        return AccountMapper.toDto(updated);
    }
}
