package com.nanou.yaraBank.security.jwtToken;


import com.nanou.yaraBank.security.refreshToken.RefreshToken;
import com.nanou.yaraBank.user.UserDomain;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JwtToken {
    @Id
    @UuidGenerator()
    @Column(nullable = false, updatable = false)
    private String id;
    @Column(columnDefinition = "TEXT")
    private String value;
    private Boolean enable;
    private Boolean expire;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private RefreshToken refreshToken;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    private UserDomain user;


}
