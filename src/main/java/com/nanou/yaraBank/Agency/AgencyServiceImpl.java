package com.nanou.yaraBank.Agency;

import com.nanou.yaraBank.exception.Response.Response;
import com.nanou.yaraBank.method.MethodService;
import com.nanou.yaraBank.user.UserDomain;
import com.nanou.yaraBank.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

import static com.nanou.yaraBank.exception.Response.Message.*;
@Service
@Transactional
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService{
   private final AgencyRepository agencyRepository;
   private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final MethodService methodService;



    //Generation de l id Agence
    @Override
    public String agencyRef(){
        Random random = new Random();
        int randomInteger = random.nextInt(99);
        String codeAgency = INDICE+String.format("%02d", +randomInteger);
        return codeAgency;
    }

   //Creation d'un Compte Agency.
    @Override
    public Response addAgency(AgencyDomain agencyDomain) {
      //  methodService.addChecking(agencyDomain.getMail(), agencyDomain.getPhone(),agencyDomain.getU());
        AgencyUser agencyUser = new AgencyUser();
        agencyUser.setCity(agencyDomain.getCity());
        agencyUser.setCountry(agencyDomain.getCountry());
        agencyUser.setLastName(agencyDomain.getName());
        agencyUser.setMail(agencyDomain.getMail());
        agencyUser.setPhone(agencyDomain.getPhone());
        agencyUser.setUsername(agencyDomain.getMail());
        agencyUser.setPassword(encoder.encode(agencyDomain.getPassword()));
        agencyDomain.setRefNumber(agencyRef());
        userRepository.save(agencyUser);
        AgencyDomain save = agencyRepository.save(agencyDomain);
        return new Response(OK,AGENCY_SUCCESS,save);
    }

    //Creation d'un Compte Agency.



}
