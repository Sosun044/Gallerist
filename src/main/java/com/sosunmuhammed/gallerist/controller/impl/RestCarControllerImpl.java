package com.sosunmuhammed.gallerist.controller.impl;

import com.sosunmuhammed.gallerist.controller.IRestCarController;
import com.sosunmuhammed.gallerist.controller.RestBaseController;
import com.sosunmuhammed.gallerist.controller.RootEntity;
import com.sosunmuhammed.gallerist.dto.DtoCar;
import com.sosunmuhammed.gallerist.dto.DtoCarUI;
import com.sosunmuhammed.gallerist.service.ICarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements IRestCarController {

    @Autowired
    private ICarService carService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoCar> save(@Valid @RequestBody DtoCarUI dtoCarUI) {
        return ok(carService.save(dtoCarUI));
    }

    @GetMapping("/list")
    @Override
    public RootEntity<List<DtoCar>> getAll() {
        return ok(carService.getAll());
    }
    @GetMapping("/find/{id}")
    @Override
    public RootEntity<DtoCar> findCar(@PathVariable Long id) {
        return ok(carService.findCar(id));
    }
    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<String> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ok("Record is delete successfully");
    }
    @PutMapping("/update/{id}")
    @Override
    public RootEntity<DtoCar> updateCar(@PathVariable Long id,@Valid @RequestBody DtoCarUI dtoCarUI) {
        return ok(carService.updateCar(id,dtoCarUI));
    }
}
