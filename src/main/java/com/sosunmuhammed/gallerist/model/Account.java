package com.sosunmuhammed.gallerist.model;


import com.sosunmuhammed.gallerist.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account extends BaseEntity{

    @Column(name = "account_No")
    private String accountNo;
    @Column(name = "iban")
    private String iban;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "currency_type")
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;


}
