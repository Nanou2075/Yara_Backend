package com.nanou.yaraBank.Agency;

import com.nanou.yaraBank.user.UserDomain;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@DiscriminatorValue("AGENCY")
public class AgencyUser extends UserDomain {
}
