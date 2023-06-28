package com.memposit.auth.oauth.authority.security.oauth2.handler;

import com.memposit.auth.oauth.authority.exeption.OAuth2AuthenticationProcessingException;
import com.memposit.user.service.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

/**
 * The type Custom oidc user service.
 */
@Service
public class CustomOidcUserService extends OidcUserService {

    private final UserServiceImpl userServiceImpl;

    /**
     * Instantiates a new Custom oidc user service.
     *
     * @param userServiceImpl the user service
     */
    @Lazy
    public CustomOidcUserService(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        try {
            return userServiceImpl.processOAuth2User(userRequest.getClientRegistration().getRegistrationId(), oidcUser.getAttributes(), oidcUser.getIdToken(),
                    oidcUser.getUserInfo());
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new OAuth2AuthenticationProcessingException(ex.getMessage());
        }
    }
}