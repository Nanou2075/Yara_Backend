
package com.nanou.yaraBank.customer;

import com.nanou.yaraBank.exception.Response.Response;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface CustomerService {
    Response addCustomer (CustomerDomain customerDomain);
}
