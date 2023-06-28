package com.memposit.auth.oauth.authority.service.impl;

import com.memposit.auth.oauth.authority.entity.TokenStore;
import com.memposit.auth.oauth.authority.enums.TokenStatus;
import com.memposit.auth.oauth.authority.service.TokenService;
import com.memposit.auth.oauth.authority.repository.TokenStoreRepository;
import com.memposit.user.service.entity.User;
import com.memposit.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * The type Token service.
 */
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenStoreRepository tokenStoreRepository;
    private final UserService userService;

    @Value("${token.expired.date}")
    private int expiration;

    public void updateToken(final String token, TokenStore tokenStore) {
        tokenStore.setToken(token);
        tokenStore.setExpiryDate(calculateExpiryDate(expiration));
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    @Override
    public void createVerificationTokenForUser(final User user, final String token) {
        final TokenStore myToken = new TokenStore(token, user.getId());
        tokenStoreRepository.save(myToken);
    }

    @Override
    public boolean resendVerificationToken(final String existingVerificationToken) {
        TokenStore vToken = tokenStoreRepository.findByToken(existingVerificationToken).get();
        if (Objects.isNull(vToken)) {
            updateToken(UUID.randomUUID().toString(), vToken);
            tokenStoreRepository.save(vToken);
            return true;
        }
        return false;
    }

    @Override
    public String validateVerificationToken(String token) {
        TokenStore verificationToken = tokenStoreRepository.findByToken(token).get();
        if (verificationToken == null) {
            return TokenStatus.TOKEN_INVALID.name();
        }
        final User user = userService.getUserById(verificationToken.getUserId());
        if ((verificationToken.getExpiryDate().getTime() - Calendar.getInstance().getTime().getTime()) <= 0) {
            return TokenStatus.TOKEN_EXPIRED.name();
        }
        user.setEnabled(true);
        tokenStoreRepository.delete(verificationToken);
        userService.saveUser(Optional.of(user));
        return TokenStatus.TOKEN_VALID.name();
    }

}
