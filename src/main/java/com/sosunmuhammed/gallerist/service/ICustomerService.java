package com.sosunmuhammed.gallerist.service;

import com.sosunmuhammed.gallerist.dto.DtoCustomer;
import com.sosunmuhammed.gallerist.dto.DtoCustomerIU;

import java.util.List;

public interface ICustomerService {
    DtoCustomer save(DtoCustomerIU dtoCustomerIU);
    List<DtoCustomer> getAll();
    DtoCustomer findCustomer(Long id);
    void deleteCustomer(Long id);
    DtoCustomer update(Long id,DtoCustomerIU dtoCustomerIU);
}
