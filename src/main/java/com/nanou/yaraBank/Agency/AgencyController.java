package com.nanou.yaraBank.Agency;

import com.nanou.yaraBank.exception.Response.Response;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class AgencyController implements AgencyResource{
    private final   AgencyService agencyService;
    @Override
    public Response addAgency(AgencyDomain agencyDomain) {
       return agencyService.addAgency(agencyDomain);
    }
}
