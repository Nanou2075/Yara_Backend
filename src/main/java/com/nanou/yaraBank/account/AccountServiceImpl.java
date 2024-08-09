
package com.nanou.yaraBank.account;

import com.nanou.yaraBank.customer.CustomerDomain;
import com.nanou.yaraBank.customer.CustomerRepository;
import com.nanou.yaraBank.enums.AccountType;
import com.nanou.yaraBank.enums.CustomerType;
import com.nanou.yaraBank.enums.Role;
import com.nanou.yaraBank.exception.EntityNotFoundException;
import com.nanou.yaraBank.exception.Response.Response;
import com.nanou.yaraBank.method.MethodService;
import com.nanou.yaraBank.user.UserDomain;
import com.nanou.yaraBank.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static com.nanou.yaraBank.exception.Response.Message.*;
import static com.nanou.yaraBank.exception.Response.Security.*;
import static com.nanou.yaraBank.exception.Response.Security.OK;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final MethodService methodService;
    private final PasswordEncoder encoder;

    //Generation du number de  Compte
    @Override
    public String compteNumber(){
        Random random = new Random();
        int randomInteger = random.nextInt(999999999);
        String code = String.format("%09d", randomInteger);
        return code;
    }

    //  creation de  Compte
    @Override
    public Response createCustomerAccount(CustomerDomain customerDomain){
         methodService.addChecking(customerDomain.getMail(), customerDomain.getPhone(),customerDomain.getUsername());
        AccountDomain accountDomain = new AccountDomain();
        if (customerDomain.getAccountType().equals(AccountType.COURANT)) {
            accountDomain.setAccountType(customerDomain.getAccountType());
            accountDomain.setAccountType(customerDomain.getAccountType());
            accountDomain.setCreateTime(LocalDateTime.now());
            accountDomain.setOverDraft(OVERDRAFT);

        }else {

            accountDomain.setAccountType(customerDomain.getAccountType());
            accountDomain.setAccountType(customerDomain.getAccountType());
            accountDomain.setCreateTime(LocalDateTime.now());
            accountDomain.setInterestRate(RATE);

        }
        accountDomain.setBalance(0.0);
        accountDomain.setCustomerDomain(customerDomain);
        customerDomain.setCountNumber(OK);
        customerDomain.setPassword(encoder.encode(customerDomain.getPassword()));
        AccountDomain account = accountRepository.save(accountDomain);
        CustomerDomain user = userRepository.save(customerDomain);
        customerDomain.setIdCustomer(user.getUserid());
        customerDomain.setCustomerType(CustomerType.PERSONNEL);
        customerRepository.save(customerDomain);
        return new Response(OK,ACCOUNT_SAVE);
    }

    @Override
    public Response acountListe(AccountType accountType) {
        List<AccountDomain> accountByAccountType = accountRepository.findAccountByAccountType(accountType);
        if (accountByAccountType.isEmpty()){
            throw new EntityNotFoundException(NO,EMPTY_ACCOUNT);
        }
        return new Response(OK,accountByAccountType);
    }
    @Override
    public Response accountBalanceListe() {
        List<AccountDomain> accountBalance = accountRepository.findAccountByCustomerDomain(methodService.getCurrentCustomer()
        );
        if (accountBalance.isEmpty()){
            throw new EntityNotFoundException(NO,EMPTY_ACCOUNT);
        }
        return new Response(OK,accountBalance);
    }

    //  creation de  Compte pour un Client existent
    @Override
    public Response createCustomerAccountEXIT(String accountID, AccountType accountType){
        String numberAccount =compteNumber() ;
        AccountDomain newAccountDomain = accountRepository.findByAccountNumber(accountID);
        if (newAccountDomain==null) {
            throw new EntityNotFoundException(NO,ACCOUNT_CHECKING);

        }

       if (newAccountDomain.getCustomerDomain().getCustomerType().equals(CustomerType.PERSONNEL)){
           AccountDomain account = newAccountDomain;
           CustomerDomain  customerDomain=account.getCustomerDomain();
           AccountDomain accountDomain = new AccountDomain();
           if (accountType.equals(AccountType.COURANT)) {
               accountDomain.setAccountType(accountType);
               accountDomain.setCreateTime(LocalDateTime.now());
               accountDomain.setOverDraft(OVERDRAFT);

           }else {
               accountDomain.setAccountType(accountType);
               accountDomain.setAccountNumber(numberAccount);
               accountDomain.setCreateTime(LocalDateTime.now());
               accountDomain.setInterestRate(RATE);
           }
           accountDomain.setBalance(0.0);
           accountDomain.setCustomerDomain(account.getCustomerDomain());
           customerDomain.setCustomerType(CustomerType.PERSONNEL);
           customerDomain.setCountNumber(customerDomain.getCountNumber()+OK);
           accountRepository.save(accountDomain);
           CustomerDomain user = userRepository.save(customerDomain);
           customerDomain.setIdCustomer(user.getUserid());
           customerRepository.save(customerDomain);
       }else {
           AccountDomain account = newAccountDomain;
           CustomerDomain  customerDomain=account.getCustomerDomain();
           AccountDomain accountDomain = new AccountDomain();
           if (accountType.equals(AccountType.COURANT)) {
               accountDomain.setAccountType(accountType);
               accountDomain.setCreateTime(LocalDateTime.now());
               accountDomain.setOverDraft(OVERDRAFT);

           }else {
               accountDomain.setAccountType(accountType);
               accountDomain.setAccountNumber(numberAccount);
               accountDomain.setCreateTime(LocalDateTime.now());
               accountDomain.setInterestRate(RATE);
           }
           accountDomain.setBalance(0.0);
           accountDomain.setCustomerDomain(account.getCustomerDomain());
           customerDomain.setCustomerType(CustomerType.ENTREPRISE);
           customerDomain.setCountNumber(customerDomain.getCountNumber()+OK);
           accountRepository.save(accountDomain);
           CustomerDomain user = userRepository.save(customerDomain);
           customerDomain.setIdCustomer(user.getUserid());
           customerRepository.save(customerDomain);

       }
        return new Response(OK,ACCOUNT_SAVE);

        }




    @Override
    public Response createEntrepriseAccount(CustomerDomain customerDomain){
        methodService.addChecking(customerDomain.getMail(), customerDomain.getPhone(),customerDomain.getUsername());
        AccountDomain accountDomain = new AccountDomain();
        if (customerDomain.getAccountType().equals(AccountType.COURANT)) {
            accountDomain.setAccountType(customerDomain.getAccountType());
            accountDomain.setAccountType(customerDomain.getAccountType());
            accountDomain.setCreateTime(LocalDateTime.now());
            accountDomain.setOverDraft(OVERDRAFT);

        }else {

            accountDomain.setAccountType(customerDomain.getAccountType());
            accountDomain.setAccountType(customerDomain.getAccountType());
            accountDomain.setCreateTime(LocalDateTime.now());
            accountDomain.setInterestRate(RATE);

        }
        accountDomain.setBalance(0.0);
        accountDomain.setCustomerDomain(customerDomain);
        customerDomain.setCountNumber(OK);
        customerDomain.setPassword(encoder.encode(customerDomain.getPassword()));
         accountRepository.save(accountDomain);
        CustomerDomain user = userRepository.save(customerDomain);
        customerDomain.setIdCustomer(user.getUserid());
        customerDomain.setCustomerType(CustomerType.ENTREPRISE);
        customerRepository.save(customerDomain);
        return new Response(OK,ACCOUNT_SAVE);
    }


    @Override
    public Response activateAccount(String idAccount) {
        AccountDomain accountById = accountRepository.findAccountById(idAccount);
        List<AccountDomain> accountByCustomerDomain = accountRepository.findAccountByCustomerDomain(accountById.getCustomerDomain());
        if (accountByCustomerDomain.size()==OK){
           String numberAccount =compteNumber() ;
           AccountDomain account = accountById;
           CustomerDomain  customerDomain=account.getCustomerDomain();
           String CUSTOMER_ID = IDCUSTOMER+customerDomain.getCountNumber();
           String CUSTOMER_AGENCY = methodService.getCurrentAgencyID();
           String compteNumber =CUSTOMER_AGENCY+"-"+numberAccount+"-"+CUSTOMER_ID+DEVICE;
           accountById.setAccountNumber(compteNumber);
           accountById.setAccountStatus(true);
           UserDomain user = accountById.getCustomerDomain();
           user.setEnable(true);
           user.setUserid(numberAccount);
           user.setRole(Role.CLIENT);
           accountRepository.save(accountById);
           userRepository.save(user);
       }
       else {
           UserDomain user = accountById.getCustomerDomain();
           String numberAccount =user.getUserid() ;
           String CUSTOMER_ID = IDCUSTOMER+accountById.getCustomerDomain().getCountNumber();
           String CUSTOMER_AGENCY = methodService.getCurrentAgencyID();
           String compteNumber =CUSTOMER_AGENCY+"-"+numberAccount+"-"+CUSTOMER_ID+DEVICE  ;
           accountById.setAccountNumber(compteNumber);
            accountById.setAccountStatus(true);
           user.setEnable(true);
           user.setUserid(numberAccount);
           user.setRole(Role.CLIENT);
           accountRepository.save(accountById);
            userRepository.save(user);
        }
        return new Response(OK,USERNAME_SUCCESS_ACTIVATED);
    }



}
