package com.sosunmuhammed.gallerist.service.impl;

import com.sosunmuhammed.gallerist.dto.DtoAddress;
import com.sosunmuhammed.gallerist.dto.DtoAddressIU;
import com.sosunmuhammed.gallerist.exception.BaseException;
import com.sosunmuhammed.gallerist.exception.ErrorMessage;
import com.sosunmuhammed.gallerist.exception.MessageType;
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

    @Autowired
    private AddressRepository addressRepository;


    private Address createAddress(DtoAddressIU dtoAddressIU){
        Address address = new Address();
        address.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoAddressIU,address);

        return address;
    }

    @Override
    public DtoAddress save(DtoAddressIU dtoAddressIU) {
        DtoAddress dtoAddress = new DtoAddress();
        Address savedAddress = addressRepository.save(createAddress(dtoAddressIU));
        BeanUtils.copyProperties(savedAddress,dtoAddress);

        return dtoAddress;
    }

    @Override
    public List<DtoAddress> getAll() {
        List<Address> addresses =  addressRepository.findAll();

        return addresses.stream().map(address -> {
            DtoAddress dtoAddress = new DtoAddress();
            BeanUtils.copyProperties(address,dtoAddress);
            return dtoAddress;
        }).collect(Collectors.toList());
    }

    @Override
    public DtoAddress findById(Long id) {
        Address address = addressRepository.findById(id).
                orElseThrow(() ->new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString())
                ));
        DtoAddress dtoAddress = new DtoAddress();
        BeanUtils.copyProperties(address,dtoAddress);
        return dtoAddress;
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
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString())));
        address.setCity(dtoAddressIU.getCity());
        address.setDistrict(dtoAddressIU.getDistrict());
        address.setNeighborhood(dtoAddressIU.getNeighborhood());
        address.setStreet(dtoAddressIU.getStreet());

        Address saved = addressRepository.save(address);
        DtoAddress dtoAddress = new DtoAddress();
        BeanUtils.copyProperties(saved,dtoAddress);
        return dtoAddress;
    }


}
