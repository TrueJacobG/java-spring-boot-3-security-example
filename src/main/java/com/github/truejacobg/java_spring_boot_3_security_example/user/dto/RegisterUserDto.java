package com.github.truejacobg.java_spring_boot_3_security_example.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserDto(

        @NotBlank
        @Email
        String email,

        @NotBlank
        String password
) {

}
