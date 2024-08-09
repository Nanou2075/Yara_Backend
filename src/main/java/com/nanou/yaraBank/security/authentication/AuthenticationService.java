package com.nanou.yaraBank.security.authentication;


import com.nanou.yaraBank.exception.EntityNotFoundException;
import com.nanou.yaraBank.exception.Response.Response;
import com.nanou.yaraBank.security.password.ChangePasswordRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public interface AuthenticationService {
    //Modification de Mot de passe
    Response changePassword(ChangePasswordRequestDTO request, String name) throws EntityNotFoundException;

    void cheCkLoading(String username) ;
}
