
package com.nanou.yaraBank.operation;


import com.nanou.yaraBank.customer.CustomerDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<OperationDomain,String> {
    OperationDomain findOperationDomainById(String id);
    List<OperationDomain> findAllOperationByCustomerDomain( CustomerDomain customerDomain);
}
