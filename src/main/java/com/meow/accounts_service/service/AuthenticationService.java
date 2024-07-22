package com.meow.accounts_service.service;

import com.meow.accounts_service.dao.IdMain;
import com.meow.accounts_service.entity.request.PasswordAuthenticationData;
import com.meow.accounts_service.entity.request.TelegramAuthenticationData;

public interface AuthenticationService {
        IdMain login(PasswordAuthenticationData passwordData);
        IdMain login(TelegramAuthenticationData telegramData);
        IdMain register(PasswordAuthenticationData passwordAuthenticationData);
        IdMain register(TelegramAuthenticationData telegramAuthenticationData);
}
