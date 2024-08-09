
package com.nanou.yaraBank.customer;


import com.nanou.yaraBank.account.AccountService;
import com.nanou.yaraBank.exception.EntityNotFoundException;
import com.nanou.yaraBank.exception.Response.Response;
import com.nanou.yaraBank.user.UserDomain;
import com.nanou.yaraBank.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.nanou.yaraBank.exception.Response.Message.*;
import static com.nanou.yaraBank.exception.Response.Security.NO;


@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final AccountService accountService;
    @Override
    public Response addCustomer(CustomerDomain customerDomain) {
        if (!customerDomain.getMail().contains("@") || !customerDomain.getMail().contains(".")) {
            throw new EntityNotFoundException(NO,READY_EXIST_MAIL);
        }
        UserDomain userDomainByMail = userRepository.findUserDomainByMail(customerDomain.getMail());
        UserDomain userDomainByPhone = userRepository.findUserDomainByPhone(customerDomain.getPhone());
        if (userDomainByMail == null) {
            throw new EntityNotFoundException(NO,READY_EXIST_MAIL);
        }
        if (userDomainByPhone == null) {
            throw new EntityNotFoundException(NO,READY_EXIST_PHONE);
        }
        customerRepository.save(customerDomain);
        accountService.createCustomerAccount(customerDomain);
        userRepository.save(customerDomain);
        return new Response(OK, ACCOUNT_SAVE);
    }


}
