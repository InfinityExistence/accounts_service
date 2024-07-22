package com.meow.accounts_service.entity.request;

import com.meow.accounts_service.dao.IdMain;
import com.meow.accounts_service.service.AuthenticationService;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class TelegramAuthenticationData implements AuthenticationData {
    long auth_date;
    String first_name;
    String hash;
    long id;
    String photo_url;
    String username;

    @Override
    public String toString() {
        return "auth_date=" + auth_date + "\n"+
                "first_name=" + first_name + "\n" +
                "id=" + id + "\n"+
             "photo_url=" + photo_url + "\n" +
               "username=" + username;
    }

    @Override
    public IdMain processLogin(AuthenticationService service) {
        return service.login(this);
    }

    @Override
    public IdMain processRegister(AuthenticationService service) {
        return service.register(this);
    }
}
