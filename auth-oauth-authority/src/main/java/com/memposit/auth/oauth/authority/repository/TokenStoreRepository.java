package com.memposit.auth.oauth.authority.repository;

import com.memposit.auth.oauth.authority.entity.TokenStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Token store repository.
 */
@Repository
public interface TokenStoreRepository extends JpaRepository<TokenStore, String> {

    /**
     * Find by token optional.
     *
     * @param token the token
     * @return the optional
     */
    Optional<TokenStore> findByToken(String token);

}
