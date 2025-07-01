package com.sosunmuhammed.gallerist.controller.impl;

import com.sosunmuhammed.gallerist.controller.IRestAccountController;
import com.sosunmuhammed.gallerist.controller.RestBaseController;
import com.sosunmuhammed.gallerist.controller.RootEntity;
import com.sosunmuhammed.gallerist.dto.DtoAccount;
import com.sosunmuhammed.gallerist.dto.DtoAccountIU;
import com.sosunmuhammed.gallerist.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController {

    @Autowired
    private IAccountService accountService;


    @PostMapping("/save")
    @Override
    public RootEntity<DtoAccount> save(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
        return ok(accountService.save(dtoAccountIU));
    }

    @GetMapping("/list")
    @Override
    public RootEntity<List<DtoAccount>> getAll(DtoAccount dtoAccount) {
        return ok(accountService.getAll());
    }

    @GetMapping("/find/{id}")
    @Override
    public RootEntity<DtoAccount> findAccount(@PathVariable Long id) {
        return ok(accountService.findAccount(id));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ok("Silme işlemi başarılı");
    }
    @PutMapping("/update/{id}")
    @Override
    public RootEntity<DtoAccount> updateAccount(@PathVariable Long id,@Valid @RequestBody DtoAccountIU dtoAccountIU) {
        return ok(accountService.update(id,dtoAccountIU));
    }
}
