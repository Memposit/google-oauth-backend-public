package com.memposit.user.service.providers;

import java.util.Map;

/**
 * The User providers info.
 */
public abstract class UserProvidersInfo {
    /**
     * The Attributes.
     */
    protected Map<String, Object> attributes;

    /**
     * Instantiates a new User providers info.
     *
     * @param attributes the attributes
     */
    protected UserProvidersInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    /**
     * Gets attributes.
     *
     * @return the attributes
     */
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public abstract String getId();

    /**
     * Gets name.
     *
     * @return the name
     */
    public abstract String getName();

    /**
     * Gets email.
     *
     * @return the email
     */
    public abstract String getEmail();

    /**
     * Gets image url.
     *
     * @return the image url
     */
    public abstract String getImageUrl();
}
