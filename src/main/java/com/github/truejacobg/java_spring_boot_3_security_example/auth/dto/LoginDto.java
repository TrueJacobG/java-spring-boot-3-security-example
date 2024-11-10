package com.github.truejacobg.java_spring_boot_3_security_example.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank
        @Email
        String email,

        @NotBlank
        String password
) {

}
