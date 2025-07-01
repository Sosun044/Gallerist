package com.sosunmuhammed.gallerist.service;

import com.sosunmuhammed.gallerist.dto.DtoGallerist;
import com.sosunmuhammed.gallerist.dto.DtoGalleristUI;

import java.util.List;

public interface IGalleristService {
    DtoGallerist save(DtoGalleristUI dtoGalleristUI);
    List<DtoGallerist> getAll();
    DtoGallerist findGallerist(Long id);
    void deleteGallerist(Long id);
    DtoGallerist update(Long id,DtoGalleristUI dtoGalleristUI);
}
