package com.sosunmuhammed.gallerist.controller;

import com.sosunmuhammed.gallerist.dto.DtoGallerist;
import com.sosunmuhammed.gallerist.dto.DtoGalleristUI;

import java.util.List;

public interface IRestGalleristController {

    RootEntity<DtoGallerist> save(DtoGalleristUI dtoGalleristUI);
    RootEntity<List<DtoGallerist>> getAll();
    RootEntity<DtoGallerist> findGallerist(Long id);
    RootEntity<String> deleteGallerist(Long id);
    RootEntity<DtoGallerist> updateGallerist(Long id,DtoGalleristUI dtoGalleristUI);
}
