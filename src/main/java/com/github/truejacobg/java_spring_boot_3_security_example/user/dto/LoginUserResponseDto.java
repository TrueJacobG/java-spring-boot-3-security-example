package com.github.truejacobg.java_spring_boot_3_security_example.user.dto;


import com.github.truejacobg.java_spring_boot_3_security_example.user.entity.Role;

public record LoginUserResponseDto(String email, Role role) {

}
