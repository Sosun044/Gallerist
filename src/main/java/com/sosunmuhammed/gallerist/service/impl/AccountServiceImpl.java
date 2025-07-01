package com.sosunmuhammed.gallerist.service.impl;

import com.sosunmuhammed.gallerist.dto.DtoAccount;
import com.sosunmuhammed.gallerist.dto.DtoAccountIU;
import com.sosunmuhammed.gallerist.dto.DtoAddress;
import com.sosunmuhammed.gallerist.dto.DtoAddressIU;
import com.sosunmuhammed.gallerist.exception.BaseException;
import com.sosunmuhammed.gallerist.exception.ErrorMessage;
import com.sosunmuhammed.gallerist.exception.MessageType;
import com.sosunmuhammed.gallerist.model.Account;
import com.sosunmuhammed.gallerist.repository.AccountRepository;
import com.sosunmuhammed.gallerist.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    private Account createAccount(DtoAccountIU dtoAccountIU){
        Account account = new Account();
        account.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoAccountIU,account);
        return account;
    }

    @Override
    public DtoAccount save(DtoAccountIU dtoAccountIU) {
        DtoAccount dtoAccount = new DtoAccount();
        Account savedAccount = accountRepository.save(createAccount(dtoAccountIU));
        BeanUtils.copyProperties(savedAccount,dtoAccount);
        return dtoAccount;
    }

    @Override
    public List<DtoAccount> getAll() {
        List<Account> accounts = accountRepository.findAll();

        return accounts.stream().map(account ->{
            DtoAccount dtoAccount = new DtoAccount();
            BeanUtils.copyProperties(account,dtoAccount);
            return dtoAccount;
        }).collect(Collectors.toList());
    }

    @Override
    public DtoAccount findAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()->new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString())));
        DtoAccount dtoAccount = new DtoAccount();
        BeanUtils.copyProperties(account,dtoAccount);
        return dtoAccount;
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
        account.setAccountNo(dtoAccountIU.getAccountNo());
        account.setIban(dtoAccountIU.getIban());
        account.setAmount(dtoAccountIU.getAmount());
        account.setCurrencyType(dtoAccountIU.getCurrencyType());

        Account saveAccount = accountRepository.save(account);

        DtoAccount dtoAccount = new DtoAccount();
        BeanUtils.copyProperties(saveAccount,dtoAccount);
        return dtoAccount;
    }
}
