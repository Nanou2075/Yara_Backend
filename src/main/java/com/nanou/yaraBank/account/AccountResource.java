
package com.nanou.yaraBank.account;

import com.nanou.yaraBank.customer.CustomerDomain;
import com.nanou.yaraBank.enums.AccountType;
import com.nanou.yaraBank.exception.Response.Response;
import org.springframework.web.bind.annotation.*;

public interface AccountResource {
    @PostMapping("createCustomer")
    Response createAccount (@RequestBody CustomerDomain customerDomain);
    @GetMapping("createCustomerExist/{idAccount}/{accountType}")
    Response createAccountExit ( @PathVariable String idAccount,@PathVariable AccountType accountType);
    @GetMapping("accountActivation/{idAccount}")
    Response activateAccount(@PathVariable  String idAccount);
    @PostMapping("createEntreprise")
    Response createEntreprise (@RequestBody CustomerDomain customerDomain);
    @GetMapping("accounts/{accountType}")
    Response accountListe(@PathVariable AccountType accountType);
    @GetMapping("balance")
    Response accountBalanceListe();
}
