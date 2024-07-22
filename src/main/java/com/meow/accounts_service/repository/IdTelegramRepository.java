package com.meow.accounts_service.repository;

import com.meow.accounts_service.dao.IdTelegram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdTelegramRepository extends JpaRepository<IdTelegram, Long> {
    boolean existsById(long id);
    Optional<IdTelegram> findById(long id);


}
