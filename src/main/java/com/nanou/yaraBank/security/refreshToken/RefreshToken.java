package com.nanou.yaraBank.security.refreshToken;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RefreshToken {
    @Id
    @UuidGenerator()
    @Column(nullable = false, updatable = false)
    private String id;
    @Column(columnDefinition = "TEXT")
    private String value;
    private Instant creationDate;
    private Instant expireDate;
    private Boolean expire;

}
