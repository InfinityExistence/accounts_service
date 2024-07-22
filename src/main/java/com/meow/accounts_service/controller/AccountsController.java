package com.meow.accounts_service.controller;

import com.meow.accounts_service.dao.IdMain;
import com.meow.accounts_service.entity.request.AuthenticationBody;
import com.meow.accounts_service.entity.response.ResponseData;
import com.meow.accounts_service.exception.UnauthorizedException;
import com.meow.accounts_service.service.AccountsService;
import com.meow.accounts_service.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("v1/")
public class AccountsController {
    @Autowired
    AccountsService accountsService;
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("login")
    public ResponseData<Object> login(@RequestBody AuthenticationBody body, HttpServletRequest request) {
        IdMain res = body.getData().processLogin(authenticationService);
        request.getSession(true).setAttribute("user", res.getId());
        return ResponseData.ok();
    }

    @PostMapping("register")
    public ResponseData<Object> register(@RequestBody AuthenticationBody body, HttpServletRequest request) {
        IdMain res = body.getData().processRegister(authenticationService);
        request.getSession(true).setAttribute("user", res.getId());
        return ResponseData.ok();
    }


    @GetMapping("user-profile")
    public ResponseData<IdMain> verify(HttpServletRequest request) {
        final long id = (Long) Optional
                .ofNullable(request.getSession(false))
                .map(session -> session.getAttribute("user"))
                .orElseThrow(() -> new UnauthorizedException("Not valid session"));

        return ResponseData.ok(
                accountsService.findUser(id)
                        .orElseThrow(() -> new UnauthorizedException("Not found user")));
    }
    @PostMapping("logout")
    public ResponseData<Object> logout(HttpServletRequest request) {
        Optional.ofNullable(request.getSession(false))
                    .ifPresent(HttpSession::invalidate);

        return ResponseData.ok();
    }
}
