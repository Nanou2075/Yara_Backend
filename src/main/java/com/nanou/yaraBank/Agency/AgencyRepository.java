package com.nanou.yaraBank.Agency;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyRepository extends JpaRepository<AgencyDomain,String> {
    AgencyDomain findByMail(String mail);
}
