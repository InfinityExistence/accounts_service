package com.meow.accounts_service.entity.request;

import com.meow.accounts_service.dao.IdMain;
import com.meow.accounts_service.service.AuthenticationService;

public interface AuthenticationData {
     IdMain processLogin(AuthenticationService service);
     IdMain processRegister(AuthenticationService service);
}
