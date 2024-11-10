package com.github.truejacobg.java_spring_boot_3_security_example.auth.hashing;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HashingUtil {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int TOKEN_LENGTH = 255;
    private static final int SALT_LENGTH = 127;

    public static String hashPassword(String password, String salt) {
        return DigestUtils.sha256Hex(password + salt);
    }

    public static String generateRandomTokenValue() {
        return generateRandomValue(TOKEN_LENGTH);
    }

    public static String generateRandomSalt() {
        return generateRandomValue(SALT_LENGTH);
    }

    private static String generateRandomValue(int length) {
        return RandomStringUtils.random(length, 0, 0, true, true, null, RANDOM);
    }
}
