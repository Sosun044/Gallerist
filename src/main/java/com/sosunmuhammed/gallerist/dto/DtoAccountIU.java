package com.sosunmuhammed.gallerist.dto;

import com.sosunmuhammed.gallerist.enums.CurrencyType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DtoAccountIU {
    @NotNull
    private String accountNo;
    @NotNull
    private String iban;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private CurrencyType currencyType;
}
