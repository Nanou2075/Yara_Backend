package com.nanou.yaraBank.security.authentication;

import com.nanou.yaraBank.exception.EntityNotFoundException;
import com.nanou.yaraBank.exception.Response.Response;
import com.nanou.yaraBank.security.password.ChangePasswordRequestDTO;
import com.nanou.yaraBank.user.UserDomain;
import com.nanou.yaraBank.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import static com.nanou.yaraBank.exception.Response.Message.*;
import static com.nanou.yaraBank.exception.Response.Security.*;
import static com.nanou.yaraBank.exception.Response.Security.OK;


@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService, UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;






//Recuperation de l'utilisateur par username
    @Override
    public UserDomain loadUserByUsername(String username) throws UsernameNotFoundException, AccessDeniedException, LockedException, DisabledException{
        UserDomain user = userRepository.findUserDomainByUsername(username);
        cheCkLoading(username);
        userRepository.save(user);
        return user;
    }

//Modification de Mot de passe
    @Override
    public Response changePassword(ChangePasswordRequestDTO request, String name) throws EntityNotFoundException {
        UserDomain user = userRepository.findUserDomainByUsername(name);

        if (request.currentPassword() == null || request.currentPassword().isEmpty()){
            throw new EntityNotFoundException(NO, CURRENT_PASSWORD_IS_REQUIRED);
        }
        if (request.newPassword() == null || request.newPassword().isEmpty()){
            throw new EntityNotFoundException(NO, NEW_PASSWORD_IS_REQUIRED);
        }
        if (request.confirmPassword() == null || request.confirmPassword().isEmpty()){
            throw new EntityNotFoundException(NO, CONFIRM_PASSWORD_IS_REQUIRED);
        }

        if (!passwordEncoder.matches(request.currentPassword(), user.getPassword()))
            throw new EntityNotFoundException(NO, PASSWORD_OLD_IS_BAD);

        if (!request.newPassword().equals(request.confirmPassword())) {
            throw new EntityNotFoundException(NO,THE_BOTH_PASSWORD_IS_INVALID );
        }

    user.setPassword(passwordEncoder.encode(request.newPassword()));

    userRepository.save(user);
    return new Response(OK, PASSWORD_IS_VALID);
}


// Verification  de L'authentication
    public void cheCkLoading(String username)  {
        UserDomain user = userRepository.findUserDomainByUsername(username);
        if (user == null) {
            try {
                throw new EntityNotFoundException(NO, USER_NOT_LOGIN);
            } catch (EntityNotFoundException e) {
                throw new RuntimeException(e);
            }

        }

        if (!user.isEnable()) {
            try {
                throw new EntityNotFoundException(N, ACCOUNT_LOCKED);
            } catch (EntityNotFoundException e) {
                throw new RuntimeException(e);
            }

        }


    }

}











