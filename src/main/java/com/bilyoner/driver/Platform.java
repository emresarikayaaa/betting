package com.bilyoner.driver;

public enum Platform {
    WEB,
    ANDROID,
    IOS;

    public static Platform fromConfig(String value) {
        if (value == null || value.isBlank()) {
            return WEB;
        }
        return switch (value.trim().toUpperCase()) {
            case "ANDROID" -> ANDROID;
            case "IOS" -> IOS;
            default -> WEB;
        };
    }

    public boolean isMobile() {
        return this == ANDROID || this == IOS;
    }

    public boolean isWeb() {
        return this == WEB;
    }
}
