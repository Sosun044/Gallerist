package com.sosunmuhammed.gallerist.service.impl;

import com.sosunmuhammed.gallerist.dto.DtoAccount;
import com.sosunmuhammed.gallerist.dto.DtoAddress;
import com.sosunmuhammed.gallerist.dto.DtoCustomer;
import com.sosunmuhammed.gallerist.dto.DtoCustomerIU;
import com.sosunmuhammed.gallerist.exception.BaseException;
import com.sosunmuhammed.gallerist.exception.ErrorMessage;
import com.sosunmuhammed.gallerist.exception.MessageType;
import com.sosunmuhammed.gallerist.mapper.CustomerMapper;
import com.sosunmuhammed.gallerist.model.Account;
import com.sosunmuhammed.gallerist.model.Address;
import com.sosunmuhammed.gallerist.model.Customer;
import com.sosunmuhammed.gallerist.repository.AccountRepository;
import com.sosunmuhammed.gallerist.repository.AddressRepository;
import com.sosunmuhammed.gallerist.repository.CustomerRepository;
import com.sosunmuhammed.gallerist.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements ICustomerService {


    private final AddressRepository addressRepository;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(AddressRepository addressRepository, AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    private Customer createCustomer(DtoCustomerIU dtoCustomerIU){

        Address address = addressRepository.findById(dtoCustomerIU.getAddressId()).orElseThrow(
                ()->new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoCustomerIU.getAddressId().toString()))
        );
        Account account = accountRepository.findById(dtoCustomerIU.getAccountId()).orElseThrow(
                ()->new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoCustomerIU.getAccountId().toString()))
        );
        Customer savedCustomer = CustomerMapper.toEntity(dtoCustomerIU);
        savedCustomer.setAddress(address);
        savedCustomer.setAccount(account);

        return savedCustomer;
    }

    @Override
    public DtoCustomer save(DtoCustomerIU dtoCustomerIU) {
        Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));
        return CustomerMapper.toDto(savedCustomer);

    }

    @Override
    public List<DtoCustomer> getAll() {

        return customerRepository.findAll().stream()
                .map(CustomerMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public DtoCustomer findCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString())));
        return CustomerMapper.toDto(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()->
                new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString())));
        customerRepository.delete(customer);
    }

    @Override
    public DtoCustomer update(Long id,DtoCustomerIU dtoCustomerIU) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString())));
        CustomerMapper.updateCustomer(customer,dtoCustomerIU);
        Customer updateCustomer = customerRepository.save(customer);
        return CustomerMapper.toDto(updateCustomer);
    }

}
