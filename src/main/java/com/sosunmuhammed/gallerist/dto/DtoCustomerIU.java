package com.sosunmuhammed.gallerist.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DtoCustomerIU {

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String tckn;
    @NotNull
    private Date birthOfDate;
    @NotNull
    private Long addressId;
    @NotNull
    private Long accountId;
}
