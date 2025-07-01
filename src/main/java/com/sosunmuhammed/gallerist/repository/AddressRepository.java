package com.sosunmuhammed.gallerist.repository;

import com.sosunmuhammed.gallerist.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
