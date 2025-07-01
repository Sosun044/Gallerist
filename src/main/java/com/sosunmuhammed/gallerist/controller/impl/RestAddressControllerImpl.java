package com.sosunmuhammed.gallerist.controller.impl;

import com.sosunmuhammed.gallerist.controller.IRestAddressController;
import com.sosunmuhammed.gallerist.controller.RestBaseController;
import com.sosunmuhammed.gallerist.controller.RootEntity;
import com.sosunmuhammed.gallerist.dto.DtoAddress;
import com.sosunmuhammed.gallerist.dto.DtoAddressIU;
import com.sosunmuhammed.gallerist.service.impl.AddressServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController {

    @Autowired
    private AddressServiceImpl addressService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoAddress> savedAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
        return ok(addressService.save(dtoAddressIU));
    }

    @GetMapping("/list")
    @Override
    public RootEntity<List<DtoAddress>> getAll() {
        return ok(addressService.getAll());
    }

    @GetMapping("/find/{id}")
    @Override
    public RootEntity<DtoAddress> findById(@PathVariable Long id) {
        return ok(addressService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<String> deleteAddress(@PathVariable Long id) {
        addressService.deleteId(id);
        return ok("Address başarıyla silindi");
    }

    @PutMapping("/update/{id}")
    @Override
    public RootEntity<DtoAddress> update(@PathVariable Long id,
                                         @Valid @RequestBody DtoAddressIU dtoAddressIU) {
        return ok(addressService.update(id,dtoAddressIU));
    }
}
