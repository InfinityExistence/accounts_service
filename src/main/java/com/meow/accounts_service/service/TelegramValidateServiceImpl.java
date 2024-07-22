package com.meow.accounts_service.service;

import com.meow.accounts_service.entity.request.TelegramAuthenticationData;
import com.meow.accounts_service.exception.OutdatedException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
public class TelegramValidateServiceImpl implements TelegramValidateService {
    @Value("${bot.token}")
    private String BOT_TOKEN;
    private static final String algorithm = "HmacSHA256";

    @Override
    public boolean validate(TelegramAuthenticationData telegramData) {

        if ((System.currentTimeMillis()/1000) - telegramData.getAuth_date() > 3600)
            throw new OutdatedException("Old session (1h)");

        String hmac =  hashTelegramData(telegramData.toString());

        return telegramData.getHash().equals(hmac);
    }
    private String hashTelegramData(String data) {
        return new HmacUtils(algorithm, DigestUtils.sha256(BOT_TOKEN)).hmacHex(data);
    }
}
