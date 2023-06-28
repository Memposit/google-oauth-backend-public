package com.memposit.user.service.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * The Local user.
 */
public class LocalUser extends User implements OAuth2User {

    private transient Map<String, Object> attributes;
    private transient final com.memposit.user.service.entity.User user;

    /**
     * Instantiates a new Local user.
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
    public LocalUser(final String userID, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired,
                     final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities, com.memposit.user.service.entity.User user) {
        super(userID, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
    }

    /**
     * Create local user.
     *
     * @param user         the user
     * @param attributes   the attributes
     * @param clientSecret the client secret
     * @return the local user
     */
    public static LocalUser create(com.memposit.user.service.entity.User user, Map<String, Object> attributes, String clientSecret) {
        LocalUser localUser = new LocalUser(user.getEmail(), clientSecret, user.getEmailVerified(), true, true, true, Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName())),
                user);
        localUser.setAttributes(attributes);
        return localUser;
    }

    /**
     * Sets attributes.
     *
     * @param attributes the attributes
     */
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return this.user.getName();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public com.memposit.user.service.entity.User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LocalUser localUser = (LocalUser) o;
        return Objects.equals(attributes, localUser.attributes) && Objects.equals(user, localUser.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), attributes, user);
    }
}
