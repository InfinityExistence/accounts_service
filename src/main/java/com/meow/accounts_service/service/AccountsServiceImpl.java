package com.meow.accounts_service.service;

import com.meow.accounts_service.dao.IdEmail;
import com.meow.accounts_service.dao.IdMain;
import com.meow.accounts_service.dao.IdTelegram;
import com.meow.accounts_service.exception.UnauthorizedException;
import com.meow.accounts_service.repository.IdEmailRepository;
import com.meow.accounts_service.repository.IdMainRepository;
import com.meow.accounts_service.repository.IdTelegramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    IdMainRepository idMainRepository;
    @Autowired
    IdEmailRepository idEmailRepository;
    @Autowired
    IdTelegramRepository idTelegramRepository;

    @Override
    public Optional<IdMain> findUser(long id) {
        return idMainRepository.findById(id);
    }

    @Override
    public Optional<IdMain> findTelegramUser(long id) {
        Optional<IdTelegram> tg = idTelegramRepository.findById(id);
        return tg.map(IdTelegram::getIdmain);
    }

    @Override
    public Optional<IdMain> findEmailUser(String email) {
        Optional<IdEmail> idEmail = idEmailRepository.findByEmail(email);
        return idEmail.map(IdEmail::getIdmain);
    }

    @Override
    public IdMain addUser(IdTelegram idTelegram) {
        IdMain idMain = new IdMain();
        if (!isTelegramUserExist(idTelegram.getIdtg())) {
            idMain.setIdTelegram(idTelegram);
            idTelegram.setIdmain(idMain);
        } else
            throw new UnauthorizedException("User already exists");

        return addUser(idMain);
    }

    @Override
    public IdMain addUser(IdEmail idEmail) {
        IdMain idMain = new IdMain();
        if (!isEmailUserExist(idEmail.getEmail())) {
            idMain.setIdEmail(idEmail);
            idEmail.setIdmain(idMain);
        } else
            throw new UnauthorizedException("User already exists");
        return addUser(idMain);
    }
    @Override
    public IdMain addUser(IdMain idMain) {
        return idMainRepository.save(idMain);
    }
    @Override
    public boolean isTelegramUserExist(long id) {
        return idTelegramRepository.existsById(id);
    }
    @Override
    public boolean isEmailUserExist(String email) {
        return idEmailRepository.existsByEmail(email);
    }
    @Override
    public boolean isMainUserExist(long id) {
        return idMainRepository.existsById(id);
    }




}
