package com.sosunmuhammed.gallerist.repository;

import com.sosunmuhammed.gallerist.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
}
