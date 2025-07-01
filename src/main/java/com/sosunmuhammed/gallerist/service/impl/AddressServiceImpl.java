package com.sosunmuhammed.gallerist.service.impl;

import com.sosunmuhammed.gallerist.dto.DtoAddress;
import com.sosunmuhammed.gallerist.dto.DtoAddressIU;
import com.sosunmuhammed.gallerist.exception.BaseException;
import com.sosunmuhammed.gallerist.exception.ErrorMessage;
import com.sosunmuhammed.gallerist.exception.MessageType;
import com.sosunmuhammed.gallerist.mapper.AddressMapper;
import com.sosunmuhammed.gallerist.model.Account;
import com.sosunmuhammed.gallerist.model.Address;
import com.sosunmuhammed.gallerist.repository.AddressRepository;
import com.sosunmuhammed.gallerist.service.IAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements IAddressService {


    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public DtoAddress save(DtoAddressIU dtoAddressIU) {
        Address savedAddress = addressRepository.save(AddressMapper.toEntity(dtoAddressIU));
        return AddressMapper.toDto(savedAddress);
    }

    @Override
    public List<DtoAddress> getAll() {
        return addressRepository.findAll().stream()
                .map(AddressMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DtoAddress findById(Long id) {
        Address address = addressRepository.findById(id).
                orElseThrow(() ->new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString())
                ));
        return AddressMapper.toDto(address);
    }

    @Override
    public void deleteId(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() ->new BaseException
                        (new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString())));
        addressRepository.delete(address);
    }

    @Override
    public DtoAddress update(Long id, DtoAddressIU dtoAddressIU) {
        Address address = addressRepository.findById(id).orElseThrow(()->
            new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString())));
        AddressMapper.updateEntity(address,dtoAddressIU);
        Address updated = addressRepository.save(address);
        return AddressMapper.toDto(updated);
    }


}
