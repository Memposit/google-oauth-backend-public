package com.memposit.auth.oauth.authority.exeption;

/**
 * The type auth authentication processing exception.
 */
public class OAuth2AuthenticationProcessingException extends RuntimeException {
    /**
     * Instantiates a new auth authentication processing exception.
     *
     * @param msg the msg
     */
    public OAuth2AuthenticationProcessingException(String msg) {
        super(msg);
    }
}
