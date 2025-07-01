package com.sosunmuhammed.gallerist.repository;

import com.sosunmuhammed.gallerist.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefresTokenRepository extends JpaRepository<RefreshToken , Long> {

    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
