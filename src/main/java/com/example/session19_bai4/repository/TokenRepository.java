package com.example.session19_bai4.repository;

import com.example.session19_bai4.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository
        extends JpaRepository<Token, Long> {

    Optional<Token> findByTokenValue(
            String token
    );

    List<Token> findAllByEmployeeIdAndExpiredFalseAndRevokedFalse(
            Long employeeId
    );
}