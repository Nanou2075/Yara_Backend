
package com.nanou.yaraBank.account;

import com.nanou.yaraBank.customer.CustomerDomain;
import com.nanou.yaraBank.enums.AccountType;
import com.nanou.yaraBank.exception.Response.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface AccountService
{
    //Generation du numero de  Compte
    String compteNumber();

    //  creation de  Compte
    Response createCustomerAccount(CustomerDomain customerDomain);


    //  la liste des  Comptes
    Response acountListe(AccountType accountType);


    Response accountBalanceListe();

    //  creation de  Compte
    Response createCustomerAccountEXIT(String accountID, AccountType accountType);

    Response createEntrepriseAccount(CustomerDomain customerDomain);


    //  activation  du  Compte

    Response activateAccount(String idUser);
    //  activation  du  Compte pour un Client existent

}
