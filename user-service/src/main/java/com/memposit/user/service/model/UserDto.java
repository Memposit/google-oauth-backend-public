package com.memposit.user.service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * The type User dto.
 */
@Getter
@Setter
@Builder
public class UserDto {

    private String id;
    private String displayName;
    private String email;
    private String role;

}
