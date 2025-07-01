package com.sosunmuhammed.gallerist.controller.impl;

import com.sosunmuhammed.gallerist.controller.IRestGalleristController;
import com.sosunmuhammed.gallerist.controller.RestBaseController;
import com.sosunmuhammed.gallerist.controller.RootEntity;
import com.sosunmuhammed.gallerist.dto.DtoGallerist;
import com.sosunmuhammed.gallerist.dto.DtoGalleristUI;
import com.sosunmuhammed.gallerist.service.IGalleristService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristControllerImpl extends RestBaseController implements IRestGalleristController {

    @Autowired
    private IGalleristService galleristService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoGallerist> save(@Valid @RequestBody DtoGalleristUI dtoGalleristUI) {
        return ok(galleristService.save(dtoGalleristUI));
    }
    @GetMapping("/list")
    @Override
    public RootEntity<List<DtoGallerist>> getAll() {
        return ok(galleristService.getAll());
    }
    @GetMapping("/find/{id}")
    @Override
    public RootEntity<DtoGallerist> findGallerist(@PathVariable Long id) {
        return ok(galleristService.findGallerist(id));
    }
    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<String> deleteGallerist(@PathVariable Long id) {
        galleristService.deleteGallerist(id);
        return ok("Silme işlemi başarılı");
    }
    @PutMapping("/update/{id}")
    @Override
    public RootEntity<DtoGallerist> updateGallerist(@PathVariable Long id,@Valid @RequestBody DtoGalleristUI dtoGalleristUI) {
        return ok(galleristService.update(id,dtoGalleristUI));
    }
}
