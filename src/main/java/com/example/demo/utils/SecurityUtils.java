package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@Slf4j
public class SecurityUtils {
    public static String encryptPassword(String password) {
        return "{bcrypt}" + PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password);
    }
}
