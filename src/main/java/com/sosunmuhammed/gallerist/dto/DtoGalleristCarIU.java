package com.sosunmuhammed.gallerist.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGalleristCarIU {
    @NotNull
    private Long gallerist;
    @NotNull
    private Long car;
}

