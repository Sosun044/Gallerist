package com.sosunmuhammed.gallerist.controller;

import com.sosunmuhammed.gallerist.dto.DtoCar;
import com.sosunmuhammed.gallerist.dto.DtoCarUI;

import java.util.List;

public interface IRestCarController {

    RootEntity<DtoCar> save(DtoCarUI dtoCarUI);
    RootEntity<List<DtoCar>> getAll();
    RootEntity<DtoCar> findCar(Long id);
    RootEntity<String> deleteCar(Long id);
    RootEntity<DtoCar> updateCar(Long id,DtoCarUI dtoCarUI);
}
