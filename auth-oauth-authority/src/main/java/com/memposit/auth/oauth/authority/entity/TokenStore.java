package com.memposit.auth.oauth.authority.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import java.util.Objects;

/**
 * The Token store entity.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class TokenStore extends Token{

    /**
     * Instantiates a new Token store.
     *
     * @param token the token
     * @param idUser  the user
     */
    public TokenStore(final String token, final String userId) {
        super(token, userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TokenStore that = (TokenStore) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
