package com.sosunmuhammed.gallerist.mapper;

import com.sosunmuhammed.gallerist.dto.DtoCar;
import com.sosunmuhammed.gallerist.dto.DtoCarUI;
import com.sosunmuhammed.gallerist.model.Car;

public class CarMapper {

    public static Car toEntity(DtoCarUI dto){
        Car car = new Car();
        car.setPlaka(dto.getPlaka());
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setProductionYear(dto.getProductionYear());
        car.setPrice(dto.getPrice());
        car.setCurrencyType(dto.getCurrencyType());
        car.setDamagePrice(dto.getDamagePrice());
        car.setCarStatusType(dto.getCarStatusType());

        return car;
    }

    public static void updateCar(Car car,DtoCarUI dto) {
        car.setPlaka(dto.getPlaka());
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setProductionYear(dto.getProductionYear());
        car.setPrice(dto.getPrice());
        car.setCurrencyType(dto.getCurrencyType());
        car.setDamagePrice(dto.getDamagePrice());
        car.setCarStatusType(dto.getCarStatusType());
    }
    public static DtoCar toDto(Car car){
        DtoCar dtoCar = new DtoCar();
        dtoCar.setPlaka(car.getPlaka());
        dtoCar.setBrand(car.getBrand());
        dtoCar.setModel(car.getModel());
        dtoCar.setProductionYear(car.getProductionYear());
        dtoCar.setPrice(car.getPrice());
        dtoCar.setCurrencyType(car.getCurrencyType());
        dtoCar.setDamagePrice(car.getDamagePrice());
        dtoCar.setCarStatusType(car.getCarStatusType());

        return dtoCar;
    }
}
