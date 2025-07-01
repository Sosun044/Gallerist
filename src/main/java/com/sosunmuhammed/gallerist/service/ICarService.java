package com.sosunmuhammed.gallerist.service;

import com.sosunmuhammed.gallerist.dto.DtoCar;
import com.sosunmuhammed.gallerist.dto.DtoCarUI;

import java.util.List;

public interface ICarService {
    DtoCar save(DtoCarUI dtoCarUI);
    List<DtoCar> getAll();
    DtoCar findCar(Long id);
    void deleteCar(Long id);
    DtoCar updateCar(Long id,DtoCarUI dtoCarUI);
}
