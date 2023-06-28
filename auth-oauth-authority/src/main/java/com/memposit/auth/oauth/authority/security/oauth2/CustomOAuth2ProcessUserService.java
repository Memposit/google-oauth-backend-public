package com.memposit.auth.oauth.authority.security.oauth2;

import com.memposit.user.service.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

/**
 * The type Custom auth process user service.
 */
@Service
public class CustomOAuth2ProcessUserService extends DefaultOAuth2UserService {
    private final UserService userService;

    /**
     * Instantiates a new Custom o auth 2 process user service.
     *
     * @param userService the user service
     */
    @Lazy
    public CustomOAuth2ProcessUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        try {
            return userService.processLoadUser(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

}
