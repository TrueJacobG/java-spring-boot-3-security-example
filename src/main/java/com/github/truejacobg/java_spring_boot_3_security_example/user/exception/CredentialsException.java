package com.github.truejacobg.java_spring_boot_3_security_example.user.exception;

import com.github.truejacobg.java_spring_boot_3_security_example.exception.GlobalException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class CredentialsException extends GlobalException {
    public CredentialsException(String message) {
        super(BAD_REQUEST, message);
    }
}
