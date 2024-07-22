package com.meow.accounts_service.entity.request;

import com.meow.accounts_service.dao.IdMain;
import com.meow.accounts_service.service.AuthenticationService;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class PasswordAuthenticationData implements Authentication, AuthenticationData {
    String email;
    String password;
    boolean authenticated = false;
    Object details;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getDetails() {
        return details;
    }

    @Override
    public Object getPrincipal() {
        return email;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return email;
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
