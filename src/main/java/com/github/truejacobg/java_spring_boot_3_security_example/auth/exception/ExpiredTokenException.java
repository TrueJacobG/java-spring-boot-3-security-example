package com.github.truejacobg.java_spring_boot_3_security_example.auth.exception;

import com.github.truejacobg.java_spring_boot_3_security_example.exception.GlobalException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class ExpiredTokenException extends GlobalException {

    public ExpiredTokenException(String message) {
        super(UNAUTHORIZED, message);
    }
}
