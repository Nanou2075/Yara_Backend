package com.nanou.yaraBank.method;


import com.nanou.yaraBank.Agency.AgencyDomain;
import com.nanou.yaraBank.customer.CustomerDomain;
import com.nanou.yaraBank.exception.EntityNotFoundException;
import com.nanou.yaraBank.user.UserDomain;

public interface MethodService {

    //Generation du Username
    String generateUsername(String nom);

    //Recuperation de l'utilisateur Courant
    UserDomain getCurrentUser() throws EntityNotFoundException;

    //Recuperation de l'identifiant de L'agency
    String getCurrentAgencyID() throws EntityNotFoundException;


    void addChecking(String email, String phone,String username);

    //Recuperation de l'identifiant de L'agence
    CustomerDomain getCurrentCustomer() throws EntityNotFoundException;
}
