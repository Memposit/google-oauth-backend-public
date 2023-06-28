package com.memposit.user.service.enums;

/**
 * The enum Provider.
 */
public enum Provider {

    /**
     * Google provider.
     */
    GOOGLE("google"),
    /**
     * Local provider.
     */
    LOCAL("local");

    private final String name;

    /**
     * Gets provider type.
     *
     * @return the provider type
     */
    public String getProviderType() {
        return name;
    }

    /**
     * From string provider.
     *
     * @param text the text
     * @return the provider
     */
    public static Provider fromString(String text) {
        for (Provider provider : Provider.values()) {
            if (provider.getProviderType().equalsIgnoreCase(text)) {
                return provider;
            }
        }
        return LOCAL;
    }

    Provider(final String providerType) {
        this.name = providerType;
    }
}
