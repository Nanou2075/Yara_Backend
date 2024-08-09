
package com.nanou.yaraBank.customer;

import com.nanou.yaraBank.exception.Response.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CustomerResource {

    @PostMapping("addCustomer")
    Response addCustomer(@RequestBody CustomerDomain customerDomain);
}
