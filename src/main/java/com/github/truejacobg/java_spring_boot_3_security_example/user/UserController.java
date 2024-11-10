package com.github.truejacobg.java_spring_boot_3_security_example.user;

import com.github.truejacobg.java_spring_boot_3_security_example.user.dto.RegisterUserDto;
import com.github.truejacobg.java_spring_boot_3_security_example.user.dto.RegisterUserResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.truejacobg.java_spring_boot_3_security_example.user.entity.Role.ADMIN;
import static com.github.truejacobg.java_spring_boot_3_security_example.user.entity.Role.USER;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    ResponseEntity<RegisterUserResponseDto> register(@Valid @RequestBody RegisterUserDto registerUserDto) {
        return ResponseEntity.ok(userService.register(registerUserDto, USER));
    }

    @PostMapping("/register/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<RegisterUserResponseDto> registerAdmin(@Valid @RequestBody RegisterUserDto registerUserDto) {
        return ResponseEntity.ok(userService.register(registerUserDto, ADMIN));
    }
}
