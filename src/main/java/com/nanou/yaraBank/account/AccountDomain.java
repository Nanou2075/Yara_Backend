package com.nanou.yaraBank.account;

import com.nanou.yaraBank.customer.CustomerDomain;
import com.nanou.yaraBank.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AccountDomain{
    @Id
    @UuidGenerator()
    @Column(nullable = false, updatable = false)
    private String id;
    private Double balance;
    private LocalDateTime createTime;
    private double overDraft;
    private double interestRate;
    private boolean accountStatus;
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @ManyToOne
    private CustomerDomain customerDomain;


}
