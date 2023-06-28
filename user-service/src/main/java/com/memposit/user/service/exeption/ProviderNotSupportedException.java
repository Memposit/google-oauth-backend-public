package com.memposit.user.service.exeption;


/**
 * The type Provider not supported exception.
 */
public class ProviderNotSupportedException extends RuntimeException {

    /**
     * Instantiates a new Provider not supported exception.
     *
     * @param msg the msg
     */
    public ProviderNotSupportedException(final String msg) {
        super(msg);
    }
}
