package com.nanou.yaraBank.enterprise;

import com.nanou.yaraBank.user.UserDomain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SuggestionDomain{
    @Id
    @UuidGenerator()
    @Column(nullable = false, updatable = false)
    private String id;
    private String FullName;
    private String message;

}
