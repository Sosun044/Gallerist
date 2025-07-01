package com.sosunmuhammed.gallerist.controller;

import com.sosunmuhammed.gallerist.dto.DtoSaledCar;
import com.sosunmuhammed.gallerist.dto.DtoSaledCarIU;

public interface IRestSaledCarController {
    RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);
}
