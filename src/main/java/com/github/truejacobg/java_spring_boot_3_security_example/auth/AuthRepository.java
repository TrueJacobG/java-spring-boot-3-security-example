package com.github.truejacobg.java_spring_boot_3_security_example.auth;

import com.github.truejacobg.java_spring_boot_3_security_example.auth.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByValue(String token);
}
