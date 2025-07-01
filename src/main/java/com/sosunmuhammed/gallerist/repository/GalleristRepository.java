package com.sosunmuhammed.gallerist.repository;

import com.sosunmuhammed.gallerist.model.Gallerist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleristRepository extends JpaRepository<Gallerist,Long> {
}
