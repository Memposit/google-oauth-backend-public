package com.memposit.api.gateway.service.security;

import com.memposit.api.gateway.service.config.AppProperty;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;

/**
 * The type Token helper.
 */
@Component
@Slf4j
public class TokenHelper {
    private final Key key;

    /**
     * Instantiates a new Token helper.
     *
     * @param appProperty the app property
     */
    public TokenHelper(AppProperty appProperty) {
        this.key = Keys.hmacShaKeyFor(appProperty.getTokenSecret().getBytes());
    }

    /**
     * Gets claims.
     *
     * @param token the token
     * @return the claims
     */
    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    /**
     * Validate token boolean.
     *
     * @param authToken the auth token
     * @return the boolean
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
