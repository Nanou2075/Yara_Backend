
package com.nanou.yaraBank.account;

import com.nanou.yaraBank.customer.CustomerDomain;
import com.nanou.yaraBank.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountDomain,String> {
    AccountDomain findByAccountNumber(String accountNumber );
    AccountDomain findAccountById(String idAccount);
    List<AccountDomain> findAccountByAccountType(AccountType accountType);
    List<AccountDomain> findAccountByCustomerDomain(CustomerDomain customerDomain);
}
