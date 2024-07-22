package com.meow.accounts_service.repository;

import com.meow.accounts_service.dao.IdEmail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdEmailRepository extends JpaRepository<IdEmail, String> {
    Optional<IdEmail> findByEmail(String email);
    boolean existsByEmail(String email);
}
