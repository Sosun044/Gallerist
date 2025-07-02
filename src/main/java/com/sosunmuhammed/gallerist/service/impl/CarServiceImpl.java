package com.sosunmuhammed.gallerist.service.impl;

import com.sosunmuhammed.gallerist.dto.DtoCar;
import com.sosunmuhammed.gallerist.dto.DtoCarUI;
import com.sosunmuhammed.gallerist.exception.BaseException;
import com.sosunmuhammed.gallerist.exception.ErrorMessage;
import com.sosunmuhammed.gallerist.exception.MessageType;
import com.sosunmuhammed.gallerist.mapper.CarMapper;
import com.sosunmuhammed.gallerist.model.Car;
import com.sosunmuhammed.gallerist.repository.CarRepository;
import com.sosunmuhammed.gallerist.service.ICarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements ICarService {


    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @Override
    public DtoCar save(DtoCarUI dtoCarUI) {
        Car car = carRepository.save(CarMapper.toEntity(dtoCarUI));
        return CarMapper.toDto(car);
    }

    @Override
    public List<DtoCar> getAll() {
        List<Car> cars = carRepository.findAll();
        if (cars.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Kayıt bulunamadı"));
        }
        return cars.stream()
                .map(CarMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public DtoCar findCar(Long id) {
        Car car = carRepository.findById(id).orElseThrow(()->
                new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Kayıt Bulunamadı")));
        return CarMapper.toDto(car);
    }

    @Override
    public void deleteCar(Long id) {
        Car car = carRepository.findById(id).orElseThrow(()->
                new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Kayıt Bulunamadı")));
        carRepository.delete(car);
    }

    @Override
    public DtoCar updateCar(Long id, DtoCarUI dtoCarUI) {
        Car car=  carRepository.findById(id).orElseThrow(()->
                new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,"Record not found")));
        CarMapper.updateCar(car,dtoCarUI);
        Car updated = carRepository.save(car);
        return CarMapper.toDto(updated);
    }
}
