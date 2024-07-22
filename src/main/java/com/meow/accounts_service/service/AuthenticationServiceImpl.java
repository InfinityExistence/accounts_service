package com.meow.accounts_service.service;

import com.meow.accounts_service.dao.IdEmail;
import com.meow.accounts_service.dao.IdMain;
import com.meow.accounts_service.dao.IdTelegram;
import com.meow.accounts_service.entity.request.PasswordAuthenticationData;
import com.meow.accounts_service.entity.request.TelegramAuthenticationData;
import com.meow.accounts_service.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    TelegramValidateService telegramValidateService;
    @Autowired
    AccountsService accountsService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    private final Pattern emailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    @Override
    public IdMain register(PasswordAuthenticationData passwordAuthenticationData) {
        String email = passwordAuthenticationData.getEmail();
        String password = passwordAuthenticationData.getPassword();

        if (!isValidEmail(email) || !isValidPassword(password))
            throw new UnauthorizedException("Wrong email or password");

        if (accountsService.isEmailUserExist(email))
            throw new UnauthorizedException("User already exist");

        IdEmail idEmail = IdEmail.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();
        return accountsService.addUser(idEmail);
    }

    private boolean isValidEmail(String email) {
        return emailPattern.matcher(email).find();
    }
    private boolean isValidPassword(String password) {
        int length = password.length();
        return length > 8 && length < 128;
    }
    @Override
    public IdMain login(PasswordAuthenticationData passwordData) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(passwordData.getEmail(), passwordData.getPassword()));

        if (authenticate.isAuthenticated()) {
            Optional<IdMain> emailUser = accountsService.findEmailUser(passwordData.getEmail());
            if (emailUser.isPresent())
                return emailUser.get();
        }

        throw new UnauthorizedException("Authorization failed.");
    }

    @Override
    public IdMain register(TelegramAuthenticationData telegramAuthenticationData) {
        return login(telegramAuthenticationData);
    }
    @Override
    public IdMain login(TelegramAuthenticationData telegramData) {
        if (telegramValidateService.validate(telegramData)) {
            long id = telegramData.getId();

            Optional<IdMain> telegramUser = accountsService.findTelegramUser(id);
            return telegramUser
                    .orElseGet(() -> accountsService
                            .addUser(IdTelegram.builder().idtg(id).build()));
        }
        throw new UnauthorizedException("Authorization failed.");
    }




}
