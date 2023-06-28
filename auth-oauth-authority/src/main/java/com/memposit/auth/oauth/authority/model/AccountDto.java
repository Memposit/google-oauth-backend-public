package com.memposit.auth.oauth.authority.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {

    @JsonProperty
    private String stripeAccountId;
    private String loginLink;
    private String expressDashboardLink;
}
