package com.memposit.auth.oauth.authority.service;

import com.memposit.auth.oauth.authority.entity.TokenStore;
import com.memposit.user.service.entity.User;

/**
 * The interface Token service.
 */
public interface TokenService {

    /**
     * Update token.
     *
     * @param token      the token
     * @param tokenStore the token store
     */
    void updateToken(final String token, TokenStore tokenStore);

    /**
     * Create verification token for user.
     *
     * @param user  the user
     * @param token the token
     */
    void createVerificationTokenForUser(User user, String token);

    /**
     * Resend verification token boolean.
     *
     * @param existingVerificationToken the existing verification token
     * @return the boolean
     */
    boolean resendVerificationToken(String existingVerificationToken);

    /**
     * Validate verification token string.
     *
     * @param token the token
     * @return the string
     */
    String validateVerificationToken(String token);
}
