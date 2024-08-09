package com.nanou.yaraBank.customer;

import com.nanou.yaraBank.enums.AccountType;
import com.nanou.yaraBank.enums.CustomerType;
import com.nanou.yaraBank.user.UserDomain;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("CUSTOMER")
@Entity
public class CustomerDomain  extends UserDomain {
    private String dateOfBirth;
    private String AgencyId;
    private String idCustomer;
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;
    private int countNumber;
    private String siteWeb;
    private String nif;
}
