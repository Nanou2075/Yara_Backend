
package com.nanou.yaraBank.user;

import com.nanou.yaraBank.exception.Response.Response;

public interface UserService {
    Response addUser(UserDomain userDomain);
}
