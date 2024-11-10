package com.github.truejacobg.java_spring_boot_3_security_example.auth;

import com.github.truejacobg.java_spring_boot_3_security_example.auth.dto.LoginDto;
import com.github.truejacobg.java_spring_boot_3_security_example.auth.dto.TokenDto;
import com.github.truejacobg.java_spring_boot_3_security_example.user.dto.LoginUserResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    ResponseEntity<TokenDto> login(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }

    @PostMapping("/info")
    ResponseEntity<LoginUserResponseDto> getInfoFromToken(@RequestBody TokenDto token) {
        return ResponseEntity.ok(authService.getInfoFromToken(token.token()));
    }

    @PostMapping("/renew")
    ResponseEntity<TokenDto> renewToken(@RequestBody TokenDto token){
        return ResponseEntity.ok(authService.renewToken(token));
    }
}
