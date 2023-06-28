package com.memposit.auth.oauth.authority.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * The type Error message.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorMessage {

    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}