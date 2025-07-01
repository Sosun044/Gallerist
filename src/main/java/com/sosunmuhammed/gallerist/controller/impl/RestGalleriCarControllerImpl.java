package com.sosunmuhammed.gallerist.controller.impl;

import com.sosunmuhammed.gallerist.controller.IRestGalleristCarController;
import com.sosunmuhammed.gallerist.controller.RestBaseController;
import com.sosunmuhammed.gallerist.controller.RootEntity;
import com.sosunmuhammed.gallerist.dto.DtoGalleristCar;
import com.sosunmuhammed.gallerist.dto.DtoGalleristCarIU;
import com.sosunmuhammed.gallerist.service.IGalleristCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/gallerist-car")
public class RestGalleriCarControllerImpl extends RestBaseController implements IRestGalleristCarController {

    @Autowired
    private IGalleristCarService galleristCarService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoGalleristCar> save(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
        return ok(galleristCarService.save(dtoGalleristCarIU));
    }
    @GetMapping("/list")
    @Override
    public RootEntity<List<DtoGalleristCar>> getAll() {
        return ok(galleristCarService.getAll());
    }
    @GetMapping("/find/{id}")
    @Override
    public RootEntity<DtoGalleristCar> findGalleristCar(@PathVariable Long id) {
        return ok(galleristCarService.findGalleristCar(id));
    }
    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<String> deleteGalleristCar(@PathVariable @RequestBody Long id) {
        galleristCarService.deleteGaleristCar(id);
        return ok("Record is deleted");
    }
    @PutMapping("/update/{id}")
    @Override
    public RootEntity<DtoGalleristCar> update(@PathVariable Long id,@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
        return ok(galleristCarService.updateGalleristCar(id,dtoGalleristCarIU));
    }
}
