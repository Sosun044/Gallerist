package com.sosunmuhammed.gallerist.repository;

import com.sosunmuhammed.gallerist.model.GalleristCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleristCarRepository extends JpaRepository<GalleristCar,Long> {
}
