package com.nanou.yaraBank.method;

import com.nanou.yaraBank.Agency.AgencyDomain;
import com.nanou.yaraBank.Agency.AgencyRepository;
import com.nanou.yaraBank.customer.CustomerDomain;
import com.nanou.yaraBank.customer.CustomerRepository;
import com.nanou.yaraBank.exception.EntityNotFoundException;
import com.nanou.yaraBank.user.UserDomain;
import com.nanou.yaraBank.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

import static com.nanou.yaraBank.exception.Response.Security.*;


@RequiredArgsConstructor
@Service
public class MethodServiceImpl implements MethodService {
    private final UserRepository userRepository;
    private final AgencyRepository agencyRepository;
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;



    //Generation du Username
@Override
public String generateUsername(String nom) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        String s = RandomStringUtils.randomNumeric(OK);
        String userName = nom.replaceAll("\\s", "").toLowerCase() + "@" + year;
        UserDomain userByUsername = userRepository.findUserDomainByUsername(userName);
        if (userByUsername != null) {
            userName = nom.replaceAll("\\s", "").toLowerCase() + s + "@" + year;
        }
        return userName;
    }



    //Recuperation de l'utilisateur Courant
    @Override
    public UserDomain getCurrentUser() throws EntityNotFoundException {
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        UserDomain user = userRepository.findUserDomainByUsername(username);
        if (user == null)
            throw new EntityNotFoundException(NO, USER_NOT_FOUND);
        return user;
    }

    //Recuperation de l'identifiant de L'agence
    @Override
    public String getCurrentAgencyID() throws EntityNotFoundException {
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        AgencyDomain agencyDomain = agencyRepository.findByMail(username);
        if (agencyDomain == null)
            throw new EntityNotFoundException(NO, USER_NOT_FOUND);
        return agencyDomain.getRefNumber();
    }

    @Override
    public void addChecking(String email, String phone,String username){
        UserDomain userByMail = userRepository.findUserDomainByUsername(email);
        UserDomain userByUsername = userRepository.findUserDomainByUsername(username);
        UserDomain userByPhone = userRepository.findUserDomainByPhone(phone);
        if (!email.contains("@") || !email.contains(".")) {
            throw new EntityNotFoundException(NO, EMAIL_INVALID);
        }
        if (userByPhone != null) {
            throw new EntityNotFoundException(NO, PHONE_ALREADY_EXIST);
        }

        if (userByUsername != null) {
            throw new EntityNotFoundException(NO, USERNAME_ALREADY_EXIST);
        }

        if (userByMail != null) {
            throw new EntityNotFoundException(NO, EMAIL_ALREADY_EXIST);
        }


    }

    //Recuperation de l'identifiant de L'agence
    @Override
    public CustomerDomain getCurrentCustomer() throws EntityNotFoundException {
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        CustomerDomain customer= customerRepository.findByUsername(username);
        if (customer == null)
            throw new EntityNotFoundException(NO, USER_NOT_FOUND);
        return customer;
    }



}


