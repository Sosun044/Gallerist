package com.sosunmuhammed.gallerist.mapper;

import com.sosunmuhammed.gallerist.dto.DtoAddress;
import com.sosunmuhammed.gallerist.dto.DtoAddressIU;
import com.sosunmuhammed.gallerist.model.Address;

import java.util.Date;

public class AddressMapper {
    public static Address toEntity(DtoAddressIU dto){
        Address address = new Address();
        address.setCity(dto.getCity());
        address.setDistrict(dto.getDistrict());
        address.setNeighborhood(dto.getNeighborhood());
        address.setStreet(dto.getStreet());
        address.setCreateTime(new Date());
        return address;
    }

    public static void updateEntity(Address address,DtoAddressIU dto){
        address.setCity(dto.getCity());
        address.setDistrict(dto.getDistrict());
        address.setNeighborhood(dto.getNeighborhood());
        address.setStreet(dto.getStreet());
    }
    public static DtoAddress toDto(Address address){
        DtoAddress dtoAddress = new DtoAddress();
        dtoAddress.setId(address.getId());
        dtoAddress.setCity(address.getCity());
        dtoAddress.setDistrict(address.getDistrict());
        dtoAddress.setNeighborhood(address.getNeighborhood());
        dtoAddress.setStreet(address.getStreet());
        dtoAddress.setCreateTime(address.getCreateTime());
        return dtoAddress;
    }

}
