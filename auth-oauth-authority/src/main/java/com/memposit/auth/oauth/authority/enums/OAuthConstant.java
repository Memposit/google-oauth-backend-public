package com.memposit.auth.oauth.authority.enums;

/**
 * The enum auth constant.
 */
public enum OAuthConstant {
    /**
     * Authorization request cookie name  auth constant.
     */
    OAUTH2_AUTHORIZATION_REQUEST_URL_COOKIE_NAME("oauth2_auth_request_uri"),

    /**
     * Role o auth constant.
     */
    ROLE("role"),

    /**
     * Authenticated o auth constant.
     */
    AUTHENTICATED("authenticated"),

    /**
     * Role pre verification user o auth constant.
     */
    ROLE_PRE_VERIFICATION_USER ("ROLE_PRE_VERIFICATION_USER"),
    /**
     * Redirect uri param cookie name o auth constant.
     */
    REDIRECT_URI_COOKIE_NAME("redirect_uri");

    private final String name;
    OAuthConstant(String name) {
        this.name = name;
    }

    /**
     * Gets o auth constant.
     *
     * @return the o auth constant
     */
    public String getOAuthConstant() {
        return name;
    }
}
