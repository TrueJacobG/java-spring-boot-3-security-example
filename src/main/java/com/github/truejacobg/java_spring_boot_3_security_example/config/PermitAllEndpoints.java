package com.github.truejacobg.java_spring_boot_3_security_example.config;

import java.util.List;

public class PermitAllEndpoints {
    public static final List<String> PERMIT_ALL_ENDPOINTS = List.of("/health", "/api/v1/user/register", "/api/v1/auth/login");
}
