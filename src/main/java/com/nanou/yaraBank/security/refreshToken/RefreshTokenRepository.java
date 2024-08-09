package com.nanou.yaraBank.security.refreshToken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    
    void deleteAllByExpire(boolean expire);


}
