package com.nanou.yaraBank.Agency;

import com.nanou.yaraBank.exception.Response.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AgencyResource {
    @PostMapping("addAgency")
    Response addAgency(@RequestBody AgencyDomain agencyDomain);
}
