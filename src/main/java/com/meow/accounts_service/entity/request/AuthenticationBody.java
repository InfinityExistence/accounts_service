package com.meow.accounts_service.entity.request;

import com.meow.accounts_service.exception.UnauthorizedException;
import lombok.Data;

@Data
public class AuthenticationBody {

    PasswordAuthenticationData passwordAuthenticationData;
    TelegramAuthenticationData telegramData;

    public AuthenticationData getData() {
        if (isPasswordAuthentication())
            return passwordAuthenticationData;
        else if (isTelegramAuthentication())
            return telegramData;
        throw new UnauthorizedException("Bad data");
    }

    public boolean isPasswordAuthentication(){
        return passwordAuthenticationData != null;
    }
    public boolean isTelegramAuthentication(){
        return telegramData != null;
    }

}

