package com.sosunmuhammed.gallerist.mapper;

import com.sosunmuhammed.gallerist.dto.DtoGalleristCar;
import com.sosunmuhammed.gallerist.dto.DtoGalleristCarIU;
import com.sosunmuhammed.gallerist.model.GalleristCar;

import java.util.Date;

public class GalleristCarMapper {
    public static GalleristCar toEntity(DtoGalleristCarIU dto){
        GalleristCar galleristCar = new GalleristCar();
        galleristCar.setCreateTime(new Date());

        return galleristCar;
    }
    public static void updateGalleristCar(GalleristCar galleristCar,DtoGalleristCarIU dto){}

    public static DtoGalleristCar toDto(GalleristCar galleristCar){
       DtoGalleristCar dto = new DtoGalleristCar();
       dto.setId(galleristCar.getId());
       dto.setCreateTime(galleristCar.getCreateTime());
       dto.setGallerist(GalleristMapper.toDto(galleristCar.getGallerist()));
       dto.setCar(CarMapper.toDto(galleristCar.getCar()));
        return dto;
    }
}
