package com.github.truejacobg.java_spring_boot_3_security_example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public abstract class GlobalException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;
}
