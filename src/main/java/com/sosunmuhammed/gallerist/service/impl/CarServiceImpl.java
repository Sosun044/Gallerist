package com.sosunmuhammed.gallerist.service.impl;

import com.sosunmuhammed.gallerist.dto.DtoCar;
import com.sosunmuhammed.gallerist.dto.DtoCarUI;
import com.sosunmuhammed.gallerist.exception.BaseException;
import com.sosunmuhammed.gallerist.exception.ErrorMessage;
import com.sosunmuhammed.gallerist.exception.MessageType;
import com.sosunmuhammed.gallerist.model.Car;
import com.sosunmuhammed.gallerist.repository.CarRepository;
import com.sosunmuhammed.gallerist.service.ICarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarRepository carRepository;

    private Car createCar(DtoCarUI dtoCarUI){
        Car car = new Car();
        car.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoCarUI,car);

        return car;
    }

    @Override
    public DtoCar save(DtoCarUI dtoCarUI) {

        Car car = carRepository.save(createCar(dtoCarUI));
        DtoCar dtoCar = new DtoCar();

        BeanUtils.copyProperties(car,dtoCar);
        return dtoCar;
    }

    @Override
    public List<DtoCar> getAll() {
        List<Car> cars = carRepository.findAll();

        return cars.stream().map(car -> {
            new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,"kAYIT BULUNAMADI"));
            DtoCar dtoCar = new DtoCar();
            BeanUtils.copyProperties(car,dtoCar);
            return dtoCar;
        }).collect(Collectors.toList());
    }

    @Override
    public DtoCar findCar(Long id) {
        Optional<Car> optCar = carRepository.findById(id);
        if (optCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,"KAYIT BULUNAMADI"));
        }
        DtoCar dtoCar = new DtoCar();
        BeanUtils.copyProperties(optCar.get(),dtoCar);
        return dtoCar;
    }

    @Override
    public void deleteCar(Long id) {
        Optional<Car> optCar = carRepository.findById(id);
        if (optCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,"KAYIT BULUNAMADI"));
        }
        carRepository.delete(optCar.get());
    }

    @Override
    public DtoCar updateCar(Long id, DtoCarUI dtoCarUI) {
        Car car=  carRepository.findById(id).orElseThrow(()->
                new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,"Record not found")));
        car.setPlaka(dtoCarUI.getPlaka());
        car.setModel(dtoCarUI.getModel());
        car.setPrice(dtoCarUI.getPrice());
        car.setDamagePrice(dtoCarUI.getDamagePrice());
        car.setProductionYear(dtoCarUI.getProductionYear());
        car.setCarStatusType(dtoCarUI.getCarStatusType());
        car.setBrand(dtoCarUI.getBrand());
        car.setCurrencyType(dtoCarUI.getCurrencyType());
        Car savedCar = carRepository.save(car);
        DtoCar dtoCar = new DtoCar();
        BeanUtils.copyProperties(savedCar,dtoCar);
        return dtoCar;
    }


}
