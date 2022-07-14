package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceInter extends UserDetailsService {
    public UserDetails loadUserByUsername(String username);
}
