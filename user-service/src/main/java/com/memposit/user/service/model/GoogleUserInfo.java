package com.memposit.user.service.model;

import com.memposit.user.service.providers.UserProvidersInfo;

import java.util.Map;

/**
 * The Google user info.
 */
public class GoogleUserInfo extends UserProvidersInfo {

    /**
     * Instantiates a new Google user info.
     *
     * @param attributes the attributes
     */
    public GoogleUserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("picture");
    }
}
