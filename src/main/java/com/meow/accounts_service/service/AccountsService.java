package com.meow.accounts_service.service;

import com.meow.accounts_service.dao.IdEmail;
import com.meow.accounts_service.dao.IdMain;
import com.meow.accounts_service.dao.IdTelegram;

import java.util.Optional;

public interface AccountsService {
    Optional<IdMain> findUser(long id);
    Optional<IdMain> findTelegramUser(long id);
    boolean isTelegramUserExist(long id);
    boolean isEmailUserExist(String email);
    boolean isMainUserExist(long id);
    Optional<IdMain> findEmailUser(String email);
    IdMain addUser(IdTelegram idTelegram);
    IdMain addUser(IdEmail idEmail);
    IdMain addUser(IdMain idMain);


}
