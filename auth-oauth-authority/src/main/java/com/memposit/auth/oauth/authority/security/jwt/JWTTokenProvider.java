package com.memposit.auth.oauth.authority.security.jwt;

import com.memposit.auth.oauth.authority.config.AppProperty;
import com.memposit.auth.oauth.authority.enums.OAuthConstant;
import com.memposit.user.service.model.LocalUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

/**
 * The Jwt token provider.
 */
@Service
@Slf4j
public class JWTTokenProvider {
    private final Key key;

    private final AppProperty appProperty;

    /**
     * Instantiates a new Jwt token provider.
     *
     * @param appProperty the app property
     */
    public JWTTokenProvider(AppProperty appProperty) {
        this.appProperty = appProperty;
        this.key = Keys.hmacShaKeyFor(appProperty.getTokenSecret().getBytes());
    }

    /**
     * Create token string.
     *
     * @param userPrincipal the user principal
     * @return the string
     */
    public String createToken(LocalUser userPrincipal) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperty.getTokenExpirationMsec());

        return Jwts.builder()
                .setSubject((userPrincipal.getUser().getId()))
                .claim(
                    OAuthConstant.ROLE.getOAuthConstant(), userPrincipal.getAuthorities().stream().findFirst())
                .claim(OAuthConstant.AUTHENTICATED.getOAuthConstant(), true)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    /**
     * Gets user id from token.
     *
     * @param token the token
     * @return the user id from token
     */
    public String getUserIdFromToken(String token) {
        Claims claims = parseClaims(token).getBody();
        return String.valueOf(claims.getSubject());
    }

    private Jws<Claims> parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }

    /**
     * Is authenticated boolean.
     *
     * @param token the token
     * @return the boolean
     */
    public Boolean isAuthenticated(String token) {
        Claims claims = parseClaims(token).getBody();
        return claims.get(OAuthConstant.AUTHENTICATED.getOAuthConstant(), Boolean.class);
    }

    /**
     * Validate token boolean.
     *
     * @param authToken the auth token
     * @return the boolean
     */
    public boolean validateToken(String authToken) {
        try {
            parseClaims(authToken);
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
