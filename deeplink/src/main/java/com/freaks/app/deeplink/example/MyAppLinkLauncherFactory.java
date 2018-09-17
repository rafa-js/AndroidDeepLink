package com.freaks.app.deeplink.example;

import com.freaks.app.deeplink.DeepLink;
import com.freaks.app.deeplink.IDeepLinkLauncher;
import com.freaks.app.deeplink.IDeepLinkLauncherFactory;

public class MyAppLinkLauncherFactory implements IDeepLinkLauncherFactory {

    @Override
    public IDeepLinkLauncher getDeepLinkLauncher(DeepLink deepLink) {
        switch ( deepLink.getAction() ) {
            case "/posts":
                return new OpenPostDeepLinkLauncher();
            case "/profile":
                return new OpenProfileDeepLinkLauncher();
            default:
                throw new IllegalArgumentException( "Unknown action " + deepLink.getAction() );
        }
    }

}
