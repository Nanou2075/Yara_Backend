

package com.nanou.yaraBank.customer;

import com.nanou.yaraBank.exception.Response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerController implements CustomerResource{
    private final CustomerService customerService;
    @Override
    public Response addCustomer(CustomerDomain customerDomain){
       return customerService.addCustomer(customerDomain);
    }
}
