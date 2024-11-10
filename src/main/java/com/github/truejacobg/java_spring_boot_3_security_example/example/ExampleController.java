package com.github.truejacobg.java_spring_boot_3_security_example.example;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/example")
public class ExampleController {

    @GetMapping("/admin/only")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("you are admin");
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<String> allAuthenticated() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok("user{%s}".formatted(authentication.getPrincipal()));
    }
}
