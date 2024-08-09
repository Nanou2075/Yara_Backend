package com.nanou.yaraBank.security.jwtToken;

import com.nanou.yaraBank.user.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.stream.Stream;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {

    JwtToken findByValue(String value);

    @Query("FROM JwtToken j WHERE  j.enable = :enable AND j.expire = :expire AND j.user.username = :username")
    Optional<JwtToken> findByValueAndEnableAndExpire(boolean enable, boolean expire, String username);

    @Query("FROM JwtToken j WHERE j.user.username = :username")
    Stream<JwtToken> findByUser(String username);
    JwtToken findByUser(UserDomain user);
    JwtToken findJwtByValue(String value);

    @Query("FROM JwtToken j WHERE j.refreshToken.value = :value")
    Optional<JwtToken> findByRefreshToken(String value);

    void deleteAllByExpireAndEnable(boolean expire, boolean enable);

    void deleteJwtByValue(String value);
    void deleteJwtByUser(UserDomain user);

}
