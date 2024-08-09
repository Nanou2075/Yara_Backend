package com.nanou.yaraBank.Agency;

import com.nanou.yaraBank.exception.Response.Response;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface AgencyService {
    Response addAgency(AgencyDomain agencyDomain);
    //Generation de l id Agence
    String agencyRef();
}
