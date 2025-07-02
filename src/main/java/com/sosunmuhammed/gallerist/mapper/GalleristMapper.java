package com.sosunmuhammed.gallerist.mapper;

import com.sosunmuhammed.gallerist.dto.DtoGallerist;
import com.sosunmuhammed.gallerist.dto.DtoGalleristCarIU;
import com.sosunmuhammed.gallerist.dto.DtoGalleristUI;
import com.sosunmuhammed.gallerist.model.Gallerist;

import java.util.Date;

public class GalleristMapper {

    public static Gallerist toEntity(DtoGalleristUI dto){
        Gallerist gallerist = new Gallerist();
        gallerist.setFirstName(dto.getFirstName());
        gallerist.setLastName(dto.getLastName());
        gallerist.setCreateTime(new Date());

        return gallerist;
    }

    public static void updateGallerist(Gallerist gallerist,DtoGalleristUI dto){
        gallerist.setFirstName(dto.getFirstName());
        gallerist.setLastName(dto.getLastName());
    }
    public static DtoGallerist toDto(Gallerist gallerist){
        DtoGallerist dto = new DtoGallerist();
        dto.setId(gallerist.getId());
        dto.setFirstName(gallerist.getFirstName());
        dto.setLastName(gallerist.getLastName());
        dto.setCreateTime(gallerist.getCreateTime());
        dto.setAddress(AddressMapper.toDto(gallerist.getAddress()));
        return dto;
    }
}
