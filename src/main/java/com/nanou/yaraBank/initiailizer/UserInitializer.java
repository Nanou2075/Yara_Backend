package com.nanou.yaraBank.initiailizer;

import com.nanou.yaraBank.Agency.AgencyDomain;
import com.nanou.yaraBank.Agency.AgencyRepository;
import com.nanou.yaraBank.Agency.AgencyUser;
import com.nanou.yaraBank.enums.Role;
import com.nanou.yaraBank.user.UserDomain;
import com.nanou.yaraBank.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Random;

import static com.nanou.yaraBank.exception.Response.Message.INDICE;

@Profile("dev")
@RequiredArgsConstructor
@Component
public class UserInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final AgencyRepository agencyRepository;
    private final PasswordEncoder encoder;


    public String agencyRef(){
        Random random = new Random();
        int randomInteger = random.nextInt(9);
        String codeAgency = INDICE+String.format("%02d", +randomInteger);
        return codeAgency;
    }

    // cette Methode a pour but de créer automatiquement le compte Administrateur
    @Override
    public void run(String... args) throws Exception {
        UserDomain username = userRepository.findUserDomainByUsername("yaraBank@gmail.com");
        if(username == null) {
            AgencyUser agency = new AgencyUser();
            agency.setUsername("yaraBank@gmail.com");
            agency.setPassword(encoder.encode("123456"));
            agency.setMail("yaraBank@gmail.com");
            agency.setPhone("77573338");
            agency.setEnable(true);
            agency.setLastName("YARA");
            agency.setFirstName("Aminata");
            agency.setCity("Bamako");
            agency.setTown("Bamako");
            agency.setCountry("Mali");
            agency.setDoor("12");
            agency.setStreet("10");
            agency.setStreet("10");
            agency.setRole(Role.AGENCE);
            AgencyUser save = userRepository.save(agency);
            AgencyDomain agencyDomain = new AgencyDomain();
            agencyDomain.setRefNumber(agencyRef());
            agencyDomain.setMail(save.getMail());
            agencyDomain.setCity(save.getCity());
            agencyDomain.setCountry(save.getCountry());
            agencyDomain.setName(save.getLastName());
            agencyDomain.setPhone(save.getPhone());
            agencyDomain.setPassword(save.getPassword());
            agencyRepository.save(agencyDomain);
        }

    }
}
