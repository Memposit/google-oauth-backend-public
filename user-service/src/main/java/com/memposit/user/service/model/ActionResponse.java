package com.memposit.user.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The Action response.
 */
@Getter
@Setter
@AllArgsConstructor
public class ActionResponse {

    private boolean success;
    private String message;

}
