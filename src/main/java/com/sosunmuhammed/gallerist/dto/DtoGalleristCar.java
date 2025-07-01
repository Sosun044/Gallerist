package com.sosunmuhammed.gallerist.dto;

import com.sosunmuhammed.gallerist.model.Car;
import com.sosunmuhammed.gallerist.model.Gallerist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGalleristCar extends DtoBase{

    private DtoGallerist gallerist;

    private DtoCar car;
}
