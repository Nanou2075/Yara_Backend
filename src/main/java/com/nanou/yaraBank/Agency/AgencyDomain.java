package com.nanou.yaraBank.Agency;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AgencyDomain {
    @Id
    @UuidGenerator()
    @Column(nullable = false, updatable = false)
    private String id;
    private String name;
    private String phone;
    @Transient
    private String password;
    private String mail;
    private String refNumber;
    private String siteWeb;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean isEnable;
    private String city;
    private String country;
}
