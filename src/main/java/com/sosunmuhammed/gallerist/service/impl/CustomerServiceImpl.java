package com.sosunmuhammed.gallerist.service.impl;

import com.sosunmuhammed.gallerist.dto.DtoAccount;
import com.sosunmuhammed.gallerist.dto.DtoAddress;
import com.sosunmuhammed.gallerist.dto.DtoCustomer;
import com.sosunmuhammed.gallerist.dto.DtoCustomerIU;
import com.sosunmuhammed.gallerist.exception.BaseException;
import com.sosunmuhammed.gallerist.exception.ErrorMessage;
import com.sosunmuhammed.gallerist.exception.MessageType;
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

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer createCustomer(DtoCustomerIU dtoCustomerIU){

        Optional<Address> optAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
        if (optAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoCustomerIU.getAddressId().toString()));
        }
        Optional<Account> optAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
        if (optAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoCustomerIU.getAccountId().toString()));
        }

        Customer customer = new Customer();
        customer.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoCustomerIU,customer);
        customer.setAddress(optAddress.get());
        customer.setAccount(optAccount.get());

        return customer;
    }

    @Override
    public DtoCustomer save(DtoCustomerIU dtoCustomerIU) {
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAddress dtoAddress = new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();

        Customer savedCustomer =  customerRepository.save(createCustomer(dtoCustomerIU));

        BeanUtils.copyProperties(savedCustomer,dtoCustomer);
        BeanUtils.copyProperties(savedCustomer.getAddress(),dtoAddress);
        BeanUtils.copyProperties(savedCustomer.getAccount(),dtoAccount);

        dtoCustomer.setAddress(dtoAddress);
        dtoCustomer.setAccount(dtoAccount);

        return dtoCustomer;

    }

    @Override
    public List<DtoCustomer> getAll() {
        List<Customer> customerList = customerRepository.findAll();

        return customerList.stream().map(customer -> {
            DtoAddress dtoAddress = new DtoAddress();
            DtoAccount dtoAccount = new DtoAccount();
            DtoCustomer dtoCustomer = new DtoCustomer();
            BeanUtils.copyProperties(customer,dtoCustomer);
            BeanUtils.copyProperties(customer.getAccount(),dtoAccount);
            BeanUtils.copyProperties(customer.getAddress(),dtoAddress);

            dtoCustomer.setAccount(dtoAccount);
            dtoCustomer.setAddress(dtoAddress);
            return dtoCustomer;
        }).collect(Collectors.toList());
    }


    @Override
    public DtoCustomer findCustomer(Long id) {
        Optional<Customer> optCustomer = customerRepository.findById(id);

        if (optCustomer.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()));
        }
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAddress dtoAddress = new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();
        BeanUtils.copyProperties(optCustomer.get(),dtoCustomer);
        BeanUtils.copyProperties(optCustomer.get().getAddress(),dtoAddress);
        BeanUtils.copyProperties(optCustomer.get().getAccount(),dtoAccount);
        dtoCustomer.setAddress(dtoAddress);
        dtoCustomer.setAccount(dtoAccount);

        return dtoCustomer;
    }

    @Override
    public void deleteCustomer(Long id) {
        Optional<Customer> optCustomer = customerRepository.findById(id);
        if (optCustomer.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()));
        }
        customerRepository.delete(optCustomer.get());
    }

    @Override
    public DtoCustomer update(Long id,DtoCustomerIU dtoCustomerIU) {
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAddress dtoAddress = new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString())));
        customer.setFirstName(dtoCustomerIU.getFirstName());
        customer.setLastName(dtoCustomerIU.getLastName());
        customer.setTckn(dtoCustomerIU.getTckn());
        customer.setBirthOfDate(dtoCustomerIU.getBirthOfDate());

        Customer savedCustomer = customerRepository.save(customer);
        BeanUtils.copyProperties(savedCustomer,dtoCustomer);
        BeanUtils.copyProperties(savedCustomer.getAccount(),dtoAccount);
        BeanUtils.copyProperties(savedCustomer.getAddress(),dtoAddress);
        dtoCustomer.setAddress(dtoAddress);
        dtoCustomer.setAccount(dtoAccount);
        return dtoCustomer;
    }

}
