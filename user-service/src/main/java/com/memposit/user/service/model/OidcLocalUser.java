package com.memposit.user.service.model;

import com.memposit.user.service.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * The type Oidc local user.
 */
@Getter
@Setter
public class OidcLocalUser extends LocalUser implements OidcUser {

    private OidcIdToken idToken;
    private OidcUserInfo userInfo;

    /**
     * Instantiates a new Oidc local user.
     *
     * @param userID                the user id
     * @param password              the password
     * @param enabled               the enabled
     * @param accountNonExpired     the account non expired
     * @param credentialsNonExpired the credentials non expired
     * @param accountNonLocked      the account non locked
     * @param authorities           the authorities
     * @param user                  the user
     */
    public OidcLocalUser(String userID, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, User user) {
        super(userID, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities, user);
    }

    /**
     * Create oidc local user.
     *
     * @param user       the user
     * @param attributes the attributes
     * @param idToken    the id token
     * @param userInfo   the user info
     * @return the oidc local user
     */
    public static OidcLocalUser create(User user, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
        OidcLocalUser localUser = new OidcLocalUser(user.getEmail(), user.getPassword(), user.getEmailVerified(), true, true, true, Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName())),
                user);
        localUser.setUserInfo(userInfo);
        localUser.setIdToken(idToken);
        localUser.setAttributes(attributes);
        localUser.setIdToken(idToken);
        return localUser;
    }

    @Override
    public Map<String, Object> getClaims() {
        return super.getAttributes();
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return this.userInfo;
    }

    @Override
    public OidcIdToken getIdToken() {
        return this.idToken;
    }
}
