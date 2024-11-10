package com.github.truejacobg.java_spring_boot_3_security_example.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {GlobalException.class})
    protected ResponseEntity<Object> handleAppException(GlobalException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ExceptionDto(ex.getHttpStatus(), ex.getMessage()), new HttpHeaders(), ex.getHttpStatus(), request);
    }

    private record ExceptionDto(HttpStatus status, String detail) {
    }
}
