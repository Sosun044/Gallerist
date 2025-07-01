package com.sosunmuhammed.gallerist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "tckn")
    private String tckn;
    @Column(name = "birth_of_date")
    private Date birthOfDate;
    @OneToOne
    private Address address;
    @OneToOne
    private Account account;
}
