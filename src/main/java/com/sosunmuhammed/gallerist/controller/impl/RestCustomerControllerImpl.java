package com.sosunmuhammed.gallerist.controller.impl;

import com.sosunmuhammed.gallerist.controller.IRestCustomerController;
import com.sosunmuhammed.gallerist.controller.RestBaseController;
import com.sosunmuhammed.gallerist.controller.RootEntity;
import com.sosunmuhammed.gallerist.dto.DtoCustomer;
import com.sosunmuhammed.gallerist.dto.DtoCustomerIU;
import com.sosunmuhammed.gallerist.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerControllerImpl extends RestBaseController implements IRestCustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoCustomer> save(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
        return ok(customerService.save(dtoCustomerIU));
    }

    @GetMapping("/list")
    @Override
    public RootEntity<List<DtoCustomer>> getAll() {
        return ok(customerService.getAll());
    }

    @GetMapping("/find/{id}")
    @Override
    public RootEntity<DtoCustomer> findCustomer(@PathVariable Long id) {
        return ok(customerService.findCustomer(id));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ok("işlem başarılı");
    }
    @PutMapping("/update/{id}")
    @Override
    public RootEntity<DtoCustomer> updateCustomer(@PathVariable Long id,@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
        return ok(customerService.update(id,dtoCustomerIU));
    }
}
