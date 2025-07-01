package com.sosunmuhammed.gallerist.controller;

import com.sosunmuhammed.gallerist.dto.DtoAddress;
import com.sosunmuhammed.gallerist.dto.DtoAddressIU;
import com.sosunmuhammed.gallerist.model.Address;

import java.util.List;
import java.util.Optional;

public interface IRestAddressController {
    RootEntity<DtoAddress> savedAddress(DtoAddressIU dtoAddressIU);
    RootEntity<List<DtoAddress>> getAll();
    RootEntity<DtoAddress> findById(Long id);
    RootEntity<String> deleteAddress(Long id);
    RootEntity<DtoAddress> update(Long id,DtoAddressIU dtoAddressIU);

}
