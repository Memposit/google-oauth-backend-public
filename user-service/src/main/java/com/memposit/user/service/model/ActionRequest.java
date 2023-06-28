package com.memposit.user.service.model;

import com.memposit.user.service.validator.PasswordMatches;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * The Action request.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
@Builder
public class ActionRequest {

    private String userID;

    private String providerUserId;

    private String imgUrl;

    @NotEmpty
    private String displayName;

    @NotEmpty
    private String email;

    private String provider;

    @Size(min = 6)
    private String password;

    private String role;

    private String matchingPassword;

}
