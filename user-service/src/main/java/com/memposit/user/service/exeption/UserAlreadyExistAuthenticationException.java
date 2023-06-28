package com.memposit.user.service.exeption;

import org.springframework.security.core.AuthenticationException;

/**
 * The type User already exist authentication exception.
 */
public class UserAlreadyExistAuthenticationException extends AuthenticationException {

    /**
     * Instantiates a new User already exist authentication exception.
     *
     * @param msg the msg
     */
    public UserAlreadyExistAuthenticationException(final String msg) {
        super(msg);
    }
}