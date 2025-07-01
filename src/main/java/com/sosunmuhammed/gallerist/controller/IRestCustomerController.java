package com.sosunmuhammed.gallerist.controller;

import com.sosunmuhammed.gallerist.dto.DtoCustomer;
import com.sosunmuhammed.gallerist.dto.DtoCustomerIU;

import java.util.List;

public interface IRestCustomerController {

    RootEntity<DtoCustomer> save(DtoCustomerIU dtoCustomerIU);
    RootEntity<List<DtoCustomer>> getAll();
    RootEntity<DtoCustomer> findCustomer(Long id);
    RootEntity<String> deleteCustomer(Long id);
    RootEntity<DtoCustomer> updateCustomer(Long id,DtoCustomerIU dtoCustomerIU);
}
