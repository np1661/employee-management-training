package com.training.employeemanagementtraining.repository;

import com.training.employeemanagementtraining.entity.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken,Long> {
    boolean existsByToken(String token);
}
