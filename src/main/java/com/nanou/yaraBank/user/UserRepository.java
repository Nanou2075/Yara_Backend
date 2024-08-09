
package com.nanou.yaraBank.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDomain,String> {
    UserDomain findUserDomainByUsername(String username);
    UserDomain findUserDomainByPhone(String phone);
    UserDomain findUserDomainByMail(String mail);
    UserDomain findUserDomainByUsernameAndPassword(String username,String password);

}
