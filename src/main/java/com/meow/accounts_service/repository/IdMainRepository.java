package com.meow.accounts_service.repository;

import com.meow.accounts_service.dao.IdMain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdMainRepository extends JpaRepository<IdMain, Long> {
    boolean existsById(long id);


}
