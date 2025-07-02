    package com.sosunmuhammed.gallerist.model;

    import com.sosunmuhammed.gallerist.enums.CarStatusType;
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
    @Table(name = "car")
    public class Car extends BaseEntity{

        @Column(name = "plaka")
        private String plaka;
        @Column(name = "brand")
        private String brand;
        @Column(name = "model")
        private String model;
        @Column(name = "production_year")
        private Integer productionYear;
        @Column(name = "price")
        private BigDecimal price;
        @Column(name = "currency_type")
        @Enumerated(EnumType.STRING)
        private CurrencyType currencyType;
        @Column(name = "damage_price")
        private BigDecimal damagePrice;
        @Column(name = "car_status_type")
        @Enumerated(EnumType.STRING)
        private CarStatusType carStatusType;
    }
