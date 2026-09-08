package com.training.employeemanagementtraining.service.jwt;

import com.training.employeemanagementtraining.entity.BlacklistedToken;
import com.training.employeemanagementtraining.repository.BlacklistedTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class TokenBlacklistService {
    private final BlacklistedTokenRepository blacklistedTokenRepository;

    public void blacklistToken(String token) {

        BlacklistedToken blacklistedToken = BlacklistedToken.builder()
                .token(token)
                .blacklistedAt(LocalDateTime.now())
                .build();

        blacklistedTokenRepository.save(blacklistedToken);
    }

    public boolean isBlacklisted(String token) {
        return blacklistedTokenRepository.existsByToken(token);
    }
    }
