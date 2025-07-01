package com.sosunmuhammed.gallerist.repository;

import com.sosunmuhammed.gallerist.model.SaledCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaledCarRepository extends JpaRepository<SaledCar,Long> {
}
