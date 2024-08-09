package com.nanou.yaraBank.security.authentication;

public record RemoveAuthorisationFromUser(
        String roleName, String username
) {
}
