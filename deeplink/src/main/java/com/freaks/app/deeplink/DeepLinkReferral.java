package com.freaks.app.deeplink;

public enum DeepLinkReferral {
    WEB( 0, "Web" ),
    WHATSAPP( 1, "Whatsapp" ),
    FACEBOOK( 2, "Facebook" ),
    MESSENGER( 3, "Messenger" ),
    TWITTER( 4, "Twitter" ),
    EMAIL( 5, "Email" ),
    IN_APP( 6, "In-App" ),
    UNKNOWN( 7, "Unknown" );

    private int code;
    private String name;

    public static DeepLinkReferral getFromCode(int code) {
        if ( code >= 0 && code < 7 ) {
            return DeepLinkReferral.values()[code];
        }
        return UNKNOWN;
    }

    private DeepLinkReferral(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
