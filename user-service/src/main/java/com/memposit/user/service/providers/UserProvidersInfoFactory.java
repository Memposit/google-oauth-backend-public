package com.memposit.user.service.providers;

import com.memposit.user.service.enums.Provider;
import com.memposit.user.service.model.GoogleUserInfo;
import com.memposit.user.service.exeption.ProviderNotSupportedException;

import java.util.Map;

/**
 * The User providers info factory.
 */
public class UserProvidersInfoFactory {

    private UserProvidersInfoFactory() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Gets user info.
     *
     * @param registrationId the registration id
     * @param attributes     the attributes
     * @return the user info
     */
    public static UserProvidersInfo getUserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(Provider.GOOGLE.getProviderType())) {
            return new GoogleUserInfo(attributes);
        } else {
            throw new ProviderNotSupportedException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
