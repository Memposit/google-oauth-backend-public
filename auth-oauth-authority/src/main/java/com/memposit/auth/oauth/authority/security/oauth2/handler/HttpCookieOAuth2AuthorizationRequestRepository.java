package com.memposit.auth.oauth.authority.security.oauth2.handler;

import com.memposit.auth.oauth.authority.enums.OAuthConstant;
import com.memposit.auth.oauth.authority.util.CookieUtils;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Spring Security uses the AuthorizationRequestRepository to store OAuth2AuthorizationRequest objects during the authentication process.
 * The default implementation is the HttpSessionOAuth2AuthorizationRequestRepository class, which uses an HTTP session as storage.
 * This implementing class, which use HTTP cookies.
 */
@Component
public class HttpCookieOAuth2AuthorizationRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    @Value("${cookie.expire.seconds}")
    private int cookieExpireSeconds;

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        return CookieUtils.getCookie(request, OAuthConstant.OAUTH2_AUTHORIZATION_REQUEST_URL_COOKIE_NAME.name())
                .map(cookie -> CookieUtils.deserialize(cookie, OAuth2AuthorizationRequest.class))
                .orElse(null);
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
        if (authorizationRequest == null) {
            CookieUtils.deleteCookie(request, response, OAuthConstant.OAUTH2_AUTHORIZATION_REQUEST_URL_COOKIE_NAME.name());
            CookieUtils.deleteCookie(request, response, OAuthConstant.REDIRECT_URI_COOKIE_NAME.name());
            return;
        }

        CookieUtils.addCookie(response, OAuthConstant.OAUTH2_AUTHORIZATION_REQUEST_URL_COOKIE_NAME.name(), CookieUtils.serialize(authorizationRequest), cookieExpireSeconds);
        String redirectUriAfterLogin = request.getParameter(OAuthConstant.REDIRECT_URI_COOKIE_NAME.getOAuthConstant());
        if (StringUtils.isNotBlank(redirectUriAfterLogin)) {
            CookieUtils.addCookie(response, OAuthConstant.REDIRECT_URI_COOKIE_NAME.name(), redirectUriAfterLogin, cookieExpireSeconds);
        }
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
        return this.loadAuthorizationRequest(request);
    }

    /**
     * Remove authorization request cookies.
     *
     * @param request  the request
     * @param response the response
     */
    public void removeAuthorizationRequestCookies(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, OAuthConstant.OAUTH2_AUTHORIZATION_REQUEST_URL_COOKIE_NAME.name());
        CookieUtils.deleteCookie(request, response, OAuthConstant.REDIRECT_URI_COOKIE_NAME.name());
    }

}
