package com.github.truejacobg.java_spring_boot_3_security_example.auth.entity;

import com.github.truejacobg.java_spring_boot_3_security_example.user.entity.User;
import com.github.truejacobg.java_spring_boot_3_security_example.utils.EntityParent;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Duration;
import java.time.Instant;

@Data
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "tokens")
public class Token extends EntityParent {

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(unique = true)
    private String value;

    @NotNull
    @Default
    @Column(name = "expire_date")
    private Instant expireDate = Instant.now().plus(Duration.ofDays(1));

    @NotNull
    @Default
    private boolean removed = false;
}
