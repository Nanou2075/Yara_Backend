

package com.nanou.yaraBank.account;

import com.nanou.yaraBank.customer.CustomerDomain;
import com.nanou.yaraBank.enums.AccountType;
import com.nanou.yaraBank.exception.Response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AccountController
implements  AccountResource{
    private final  AccountService accountService;
    @Override
    public Response createAccount(CustomerDomain customerDomain) {
        return accountService.createCustomerAccount(customerDomain);
    }

    @Override
    public Response createAccountExit( String idAccount ,AccountType accountType) {
        return accountService.createCustomerAccountEXIT(idAccount,accountType);
    }

    @Override
    public Response activateAccount(String idAccount) {
        return accountService.activateAccount(idAccount);
    }

    @Override
    public Response createEntreprise(CustomerDomain customerDomain) {
        return accountService.createEntrepriseAccount(customerDomain);
    }


    @Override
    public Response accountListe(AccountType accountType) {
        return accountService.acountListe(accountType);
    }

    @Override
    public Response accountBalanceListe() {
        return accountService.accountBalanceListe();
    }
}
