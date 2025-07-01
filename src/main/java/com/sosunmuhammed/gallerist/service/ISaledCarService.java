package com.sosunmuhammed.gallerist.service;

import com.sosunmuhammed.gallerist.dto.DtoSaledCar;
import com.sosunmuhammed.gallerist.dto.DtoSaledCarIU;

public interface ISaledCarService {

    DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
}
