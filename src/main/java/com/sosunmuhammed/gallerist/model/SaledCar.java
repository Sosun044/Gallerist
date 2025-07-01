package com.sosunmuhammed.gallerist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "saled_car",uniqueConstraints = {@UniqueConstraint(columnNames = {"gallerist_id","car_id","customer_id"}
,name = "uq_gallerist_car_customer")})
public class SaledCar extends BaseEntity{

    @ManyToOne
    private Gallerist gallerist;
    @ManyToOne
    private Car car;
    @ManyToOne
    private Customer customer;
}
