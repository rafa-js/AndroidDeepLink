package com.freaks.app.deeplink.example;

import com.freaks.app.deeplink.DeepLinkHandler;
import com.freaks.app.deeplink.IDeepLinkLauncherFactory;

public class ExampleMain {

    public static void main(String[] args) {
        IDeepLinkLauncherFactory deepLinkLauncherFactory = new MyAppLinkLauncherFactory();

        DeepLinkHandler deepLinkHandler =
                DeepLinkHandler.createWithDefaultParser( deepLinkLauncherFactory, "https", "myapp.com" );
        deepLinkHandler.setDeepLinkListener( deepLink -> System.out.println( "INFO - Link processed: " + deepLink.getUrl() ) );

        String profileLink = "https://myapp.com/profile?id=12345678&alias=hackerman";
        deepLinkHandler.handleLink( profileLink );

        String postLink = "https://myapp.com/posts?id=87654321&title=hello%20world!&rf=4";
        deepLinkHandler.handleLink( postLink );
    }

}
