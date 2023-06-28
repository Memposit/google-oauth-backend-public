package com.memposit.auth.oauth.authority.model;

import com.memposit.user.service.model.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Token response.
 */
@Getter
@Setter
@NoArgsConstructor
public class TokenResponse {

    private String accessToken;
    private String tokenType = "Bearer";
    private UserDto user;

    /**
     * Instantiates a new Token response.
     *
     * @param token   the access token
     * @param userDto the user info data to display front-end
     */
    public TokenResponse(String token, UserDto userDto) {
        this.accessToken = token;
        this.user = userDto;
    }
}
