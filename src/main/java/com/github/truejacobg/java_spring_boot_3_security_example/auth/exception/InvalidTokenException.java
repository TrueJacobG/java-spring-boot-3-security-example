package com.github.truejacobg.java_spring_boot_3_security_example.auth.exception;

import com.github.truejacobg.java_spring_boot_3_security_example.exception.GlobalException;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public class InvalidTokenException extends GlobalException {

    public InvalidTokenException(String message) {
        super(FORBIDDEN, message);
    }
}
