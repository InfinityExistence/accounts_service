package com.meow.accounts_service.service;

import com.meow.accounts_service.entity.request.TelegramAuthenticationData;

public interface TelegramValidateService {
    boolean validate(TelegramAuthenticationData telegramData);
}
