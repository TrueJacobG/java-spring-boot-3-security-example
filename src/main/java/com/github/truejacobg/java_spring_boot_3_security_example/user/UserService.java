package com.github.truejacobg.java_spring_boot_3_security_example.user;

import com.github.truejacobg.java_spring_boot_3_security_example.user.dto.RegisterUserDto;
import com.github.truejacobg.java_spring_boot_3_security_example.user.dto.RegisterUserResponseDto;
import com.github.truejacobg.java_spring_boot_3_security_example.user.entity.Role;
import com.github.truejacobg.java_spring_boot_3_security_example.user.entity.User;
import com.github.truejacobg.java_spring_boot_3_security_example.user.exception.CredentialsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

import static com.github.truejacobg.java_spring_boot_3_security_example.auth.hashing.HashingUtil.generateRandomSalt;
import static com.github.truejacobg.java_spring_boot_3_security_example.auth.hashing.HashingUtil.hashPassword;

@Service
@AllArgsConstructor
public class UserService {

    private final String INCORRECT_CREDENTIALS_MESSAGE = "Incorrect email or password for user{email=%s}";

    private final UserRepository userRepository;

    public User findByEmail(String email, String password) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CredentialsException(INCORRECT_CREDENTIALS_MESSAGE.formatted(email)));
        verifyCredentials(user, password);
        return user;
    }

    public RegisterUserResponseDto register(RegisterUserDto registerUserDto, Role role) {
        Optional<User> userFromDb = userRepository.findByEmail(registerUserDto.email());
        if (userFromDb.isPresent()) {
            throw new CredentialsException(INCORRECT_CREDENTIALS_MESSAGE.formatted(registerUserDto.email()));
        }

        var salt = generateRandomSalt();
        var hashedPassword = hashPassword(registerUserDto.password(), salt);

        var user = User.builder()
                .email(registerUserDto.email())
                .password(hashedPassword)
                .salt(salt)
                .role(role)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        userRepository.saveAndFlush(user);

        return new RegisterUserResponseDto(registerUserDto.email(), role);
    }

    private void verifyCredentials(User user, String password) {
        var hashedPassword = hashPassword(password, user.getSalt());
        if (!hashedPassword.equals(user.getPassword())) {
            throw new CredentialsException(INCORRECT_CREDENTIALS_MESSAGE.formatted(user.getEmail()));
        }
    }
}

