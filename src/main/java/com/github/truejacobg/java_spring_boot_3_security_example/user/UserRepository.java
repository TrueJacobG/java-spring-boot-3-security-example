package com.github.truejacobg.java_spring_boot_3_security_example.user;

import com.github.truejacobg.java_spring_boot_3_security_example.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
