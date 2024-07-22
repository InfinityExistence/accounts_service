package com.meow.accounts_service.service;

import com.meow.accounts_service.entity.CustomUserDetails;
import com.meow.accounts_service.repository.IdEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmailDetailsService implements UserDetailsService {
    @Autowired
    IdEmailRepository accountsService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = accountsService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User is not found"));
        return new CustomUserDetails(user.getEmail(), user.getPassword());
    }
}
