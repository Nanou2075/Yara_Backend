
package com.nanou.yaraBank.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository  extends JpaRepository<CustomerDomain,String> {
    CustomerDomain findByUsername(String username);
}
