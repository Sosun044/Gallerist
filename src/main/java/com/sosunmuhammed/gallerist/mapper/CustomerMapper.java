package com.sosunmuhammed.gallerist.mapper;

import com.sosunmuhammed.gallerist.dto.DtoAccount;
import com.sosunmuhammed.gallerist.dto.DtoAddress;
import com.sosunmuhammed.gallerist.dto.DtoCustomer;
import com.sosunmuhammed.gallerist.dto.DtoCustomerIU;
import com.sosunmuhammed.gallerist.model.Address;
import com.sosunmuhammed.gallerist.model.Customer;

import java.util.Date;

public class CustomerMapper {

    public static Customer toEntity(DtoCustomerIU dto){
        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setTckn(dto.getTckn());
        customer.setBirthOfDate(dto.getBirthOfDate());
        customer.setCreateTime(new Date());
        return customer;
    }
    public static void updateCustomer(Customer customer,DtoCustomerIU dto){
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setTckn(dto.getTckn());
        customer.setBirthOfDate(dto.getBirthOfDate());
    }
    public static DtoCustomer toDto(Customer customer){
        DtoCustomer dto = new DtoCustomer();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setTckn(customer.getTckn());
        dto.setBirthOfDate(customer.getBirthOfDate());
        dto.setCreateTime(customer.getCreateTime());
        dto.setAddress(AddressMapper.toDto(customer.getAddress()));
        dto.setAccount(AccountMapper.toDto(customer.getAccount()));
        return dto;
    }

}
