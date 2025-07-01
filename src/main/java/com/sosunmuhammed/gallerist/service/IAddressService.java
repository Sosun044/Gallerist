package com.sosunmuhammed.gallerist.service;

import com.sosunmuhammed.gallerist.dto.DtoAddress;
import com.sosunmuhammed.gallerist.dto.DtoAddressIU;
import com.sosunmuhammed.gallerist.model.Address;

import java.util.List;
import java.util.Optional;

public interface IAddressService {

    DtoAddress save(DtoAddressIU dtoAddressIU);
    List<DtoAddress> getAll();
    DtoAddress findById(Long id);
    void deleteId(Long id);
    DtoAddress update(Long id, DtoAddressIU dtoAddressIU);
}
