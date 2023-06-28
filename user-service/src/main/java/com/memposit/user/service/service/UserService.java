package com.memposit.user.service.service;

import com.memposit.user.service.entity.User;
import com.memposit.user.service.exeption.UserAlreadyExistAuthenticationException;
import com.memposit.user.service.model.ActionRequest;
import com.memposit.user.service.model.LocalUser;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import java.util.Map;
import java.util.Optional;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Register new user user.
     *
     * @param actionRequest the action request
     * @return the user
     * @throws UserAlreadyExistAuthenticationException the user already exist authentication exception
     */
    User registerNewUser(ActionRequest actionRequest) throws UserAlreadyExistAuthenticationException;


    /**
     * Process o auth 2 user local user.
     *
     * @param clientRegistration the client registration
     * @param attributes         the attributes
     * @param idToken            the id token
     * @param userInfo           the user info
     * @return the local user
     */
    LocalUser processOAuth2User(String clientRegistration, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);

    /**
     * Process load user local user.
     *
     * @param clientRegistration the client registration
     * @param attributes         the attributes
     * @return the local user
     */
    LocalUser processLoadUser(String clientRegistration, Map<String, Object> attributes);

    /**
     * Save user.
     *
     * @param newUser the new user
     */
    void saveUser(Optional<User> newUser);

    User getUserById(String userId);

    void setStripeAccountId(String userId, String stripeAccountId);

    void setStripeCustomerAccount(String userId, String stripeAccountId);
}

