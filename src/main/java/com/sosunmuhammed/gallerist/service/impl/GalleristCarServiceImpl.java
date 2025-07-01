package com.sosunmuhammed.gallerist.service.impl;

import com.sosunmuhammed.gallerist.dto.*;
import com.sosunmuhammed.gallerist.exception.BaseException;
import com.sosunmuhammed.gallerist.exception.ErrorMessage;
import com.sosunmuhammed.gallerist.exception.MessageType;
import com.sosunmuhammed.gallerist.model.Car;
import com.sosunmuhammed.gallerist.model.Gallerist;
import com.sosunmuhammed.gallerist.model.GalleristCar;
import com.sosunmuhammed.gallerist.repository.CarRepository;
import com.sosunmuhammed.gallerist.repository.GalleristCarRepository;
import com.sosunmuhammed.gallerist.repository.GalleristRepository;
import com.sosunmuhammed.gallerist.service.IGalleristCarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {

    @Autowired
    private GalleristCarRepository galleristCarRepository;
    @Autowired
    private GalleristRepository galleristRepository;
    @Autowired
    private CarRepository carRepository;

    private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU){
        Optional<Gallerist> optGallerist =galleristRepository.findById(dtoGalleristCarIU.getGallerist());
        Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCar());
        if (optGallerist.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristCarIU.getGallerist().toString()));
        }
        else if (optCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristCarIU.getCar().toString()));
        }
        GalleristCar galleristCar = new GalleristCar();
        galleristCar.setCreateTime(new Date());
        galleristCar.setGallerist(optGallerist.get());
        galleristCar.setCar(optCar.get());

        return galleristCar;
    }


    @Override
    public DtoGalleristCar save(DtoGalleristCarIU dtoGalleristCarIU) {
        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();
        DtoAddress dtoAddress = new DtoAddress();
        GalleristCar galleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));

        BeanUtils.copyProperties(galleristCar,dtoGalleristCar);
        BeanUtils.copyProperties(galleristCar.getGallerist(),dtoGallerist);
        BeanUtils.copyProperties(galleristCar.getCar(),dtoCar);

        BeanUtils.copyProperties(galleristCar.getGallerist().getAddress(),dtoAddress);



        dtoGallerist.setAddress(dtoAddress);
        dtoGalleristCar.setGallerist(dtoGallerist);
        dtoGalleristCar.setCar(dtoCar);

        return dtoGalleristCar;
    }

    @Override
    public List<DtoGalleristCar> getAll() {
        List<GalleristCar> galleristCars =  galleristCarRepository.findAll();

        return galleristCars.stream().map(
                galleristCar -> { new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,galleristCar.getId().toString()));
                    DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
                    DtoGallerist dtoGallerist = new DtoGallerist();
                    DtoCar dtoCar= new DtoCar();
                    DtoAddress dtoAddress = new DtoAddress();
                    BeanUtils.copyProperties(galleristCar.getGallerist(),dtoGallerist);
                    BeanUtils.copyProperties(galleristCar.getCar(),dtoCar);
                    BeanUtils.copyProperties(galleristCar.getGallerist().getAddress(),dtoAddress);
                    BeanUtils.copyProperties(galleristCar,dtoGalleristCar);

                    dtoGallerist.setAddress(dtoAddress);
                    dtoGalleristCar.setGallerist(dtoGallerist);
                    dtoGalleristCar.setCar(dtoCar);
                    return dtoGalleristCar;

                }).collect(Collectors.toList());
    }

    @Override
    public DtoGalleristCar findGalleristCar(Long id) {
        Optional<GalleristCar> galleristCar = galleristCarRepository.findById(id);
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();
        DtoCar dtoCar = new DtoCar();
        if (galleristCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,galleristCar.toString()));
        }
        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        BeanUtils.copyProperties(galleristCar.get().getGallerist(),dtoGallerist);
        BeanUtils.copyProperties(galleristCar.get().getCar(),dtoCar);
        BeanUtils.copyProperties(galleristCar.get().getGallerist().getAddress(),dtoAddress);
        BeanUtils.copyProperties(galleristCar.get(),dtoGalleristCar);

        dtoGallerist.setAddress(dtoAddress);
        dtoGalleristCar.setCar(dtoCar);
        dtoGalleristCar.setGallerist(dtoGallerist);

        return dtoGalleristCar;
    }

    @Override
    public void deleteGaleristCar(Long id) {
        Optional<GalleristCar> optGaleristCar =  galleristCarRepository.findById(id);
        if (optGaleristCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,optGaleristCar.toString()));
        }
        galleristCarRepository.delete(optGaleristCar.get());

    }

    @Override
    public DtoGalleristCar updateGalleristCar(Long id, DtoGalleristCarIU dtoGalleristCarIU) {
        GalleristCar galleristCar = galleristCarRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString())));

        Gallerist gallerist = galleristRepository.findById(dtoGalleristCarIU.getGallerist())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getGallerist().toString())));

        Car car = carRepository.findById(dtoGalleristCarIU.getCar())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getCar().toString())));


        DtoGalleristCar dtoGalleristCar =  new DtoGalleristCar();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();
        DtoAddress dtoAddress = new DtoAddress();



        galleristCar.setGallerist(gallerist);
        galleristCar.setCar(car);

        GalleristCar savedGalleristCar = galleristCarRepository.save(galleristCar);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist(),dtoGallerist);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(),dtoAddress);
        BeanUtils.copyProperties(savedGalleristCar.getCar(),dtoCar);

        dtoGallerist.setAddress(dtoAddress);
        dtoGalleristCar.setGallerist(dtoGallerist);
        dtoGalleristCar.setCar(dtoCar);


        return dtoGalleristCar;
    }
}
