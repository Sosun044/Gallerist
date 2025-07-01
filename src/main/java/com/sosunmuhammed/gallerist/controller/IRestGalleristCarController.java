package com.sosunmuhammed.gallerist.controller;

import com.sosunmuhammed.gallerist.dto.DtoGalleristCar;
import com.sosunmuhammed.gallerist.dto.DtoGalleristCarIU;
import com.sosunmuhammed.gallerist.model.GalleristCar;

import java.util.List;

public interface IRestGalleristCarController {
    RootEntity<DtoGalleristCar> save(DtoGalleristCarIU dtoGalleristCarIU);
    RootEntity<List<DtoGalleristCar>> getAll();
    RootEntity<DtoGalleristCar> findGalleristCar(Long id);
    RootEntity<String> deleteGalleristCar(Long id);
    RootEntity<DtoGalleristCar> update(Long id,DtoGalleristCarIU dtoGalleristCarIU);
}
