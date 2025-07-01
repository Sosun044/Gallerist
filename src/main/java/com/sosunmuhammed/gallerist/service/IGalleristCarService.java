package com.sosunmuhammed.gallerist.service;

import com.sosunmuhammed.gallerist.dto.DtoGalleristCar;
import com.sosunmuhammed.gallerist.dto.DtoGalleristCarIU;
import com.sosunmuhammed.gallerist.dto.DtoGalleristUI;

import java.util.List;

public interface IGalleristCarService {

    DtoGalleristCar save(DtoGalleristCarIU dtoGalleristCarIU);
    List<DtoGalleristCar> getAll();
    DtoGalleristCar findGalleristCar(Long id);
    void deleteGaleristCar(Long id);
    DtoGalleristCar updateGalleristCar(Long id, DtoGalleristCarIU dtoGalleristCarIU);


}
