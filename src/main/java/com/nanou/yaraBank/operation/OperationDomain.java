package com.nanou.yaraBank.operation;

import com.nanou.yaraBank.account.AccountDomain;
import com.nanou.yaraBank.customer.CustomerDomain;
import com.nanou.yaraBank.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OperationDomain {
    @Id
    @UuidGenerator()
    @Column(nullable = false, updatable = false)
    private String id;
    private LocalDate operationDate;
    private double amount;
    private Boolean valid;
    @Enumerated(EnumType.STRING)
    private OperationType type;
    @ManyToOne
    private CustomerDomain customerDomain;
    private String accountNumber;
    private String description;
}
