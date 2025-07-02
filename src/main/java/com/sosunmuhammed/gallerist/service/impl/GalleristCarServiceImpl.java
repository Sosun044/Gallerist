package com.sosunmuhammed.gallerist.service.impl;

import com.sosunmuhammed.gallerist.dto.DtoGalleristCar;
import com.sosunmuhammed.gallerist.dto.DtoGalleristCarIU;
import com.sosunmuhammed.gallerist.exception.BaseException;
import com.sosunmuhammed.gallerist.exception.ErrorMessage;
import com.sosunmuhammed.gallerist.exception.MessageType;
import com.sosunmuhammed.gallerist.mapper.GalleristCarMapper;
import com.sosunmuhammed.gallerist.model.Car;
import com.sosunmuhammed.gallerist.model.Gallerist;
import com.sosunmuhammed.gallerist.model.GalleristCar;
import com.sosunmuhammed.gallerist.repository.CarRepository;
import com.sosunmuhammed.gallerist.repository.GalleristCarRepository;
import com.sosunmuhammed.gallerist.repository.GalleristRepository;
import com.sosunmuhammed.gallerist.service.IGalleristCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {


    private final GalleristCarRepository galleristCarRepository;

    private final GalleristRepository galleristRepository;

    private final CarRepository carRepository;

    public GalleristCarServiceImpl(GalleristCarRepository galleristCarRepository, GalleristRepository galleristRepository, CarRepository carRepository) {
        this.galleristCarRepository = galleristCarRepository;
        this.galleristRepository = galleristRepository;
        this.carRepository = carRepository;
    }

    private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU){
        Gallerist gallerist =galleristRepository.findById(dtoGalleristCarIU.getGallerist())
                .orElseThrow(()->new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristCarIU.getGallerist().toString())));
        Car car = carRepository.findById(dtoGalleristCarIU.getCar())
                .orElseThrow(()->new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristCarIU.getCar().toString())));
        GalleristCar galleristCar = GalleristCarMapper.toEntity(dtoGalleristCarIU);
        galleristCar.setGallerist(gallerist);
        galleristCar.setCar(car);
        return galleristCar;
    }


    @Override
    public DtoGalleristCar save(DtoGalleristCarIU dtoGalleristCarIU) {
        GalleristCar galleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));
        return GalleristCarMapper.toDto(galleristCar);
    }

    @Override
    public List<DtoGalleristCar> getAll() {
        return galleristCarRepository.findAll().stream().map(
                GalleristCarMapper::toDto
        ).collect(Collectors.toList());
    }

    @Override
    public DtoGalleristCar findGalleristCar(Long id) {
        GalleristCar galleristCar = galleristCarRepository.findById(id).orElseThrow(
                ()->new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()))
        );
        return GalleristCarMapper.toDto(galleristCar);
    }

    @Override
    public void deleteGaleristCar(Long id) {
        GalleristCar galleristCar = galleristCarRepository.findById(id).orElseThrow(
                ()->new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()))
        );
        galleristCarRepository.delete(galleristCar);

    }

    @Override
    public DtoGalleristCar updateGalleristCar(Long id, DtoGalleristCarIU dtoGalleristCarIU) {
        GalleristCar galleristCar = galleristCarRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString())));

        Gallerist gallerist = galleristRepository.findById(dtoGalleristCarIU.getGallerist())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getGallerist().toString())));

        Car car = carRepository.findById(dtoGalleristCarIU.getCar())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getCar().toString())));
        galleristCar.setGallerist(gallerist);
        galleristCar.setCar(car);
        GalleristCar updated = galleristCarRepository.save(galleristCar);
        return GalleristCarMapper.toDto(updated);
    }
}
