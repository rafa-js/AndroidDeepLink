package com.freaks.app.deeplink.example;

import com.freaks.app.deeplink.DeepLink;
import com.freaks.app.deeplink.IDeepLinkLauncher;

public class OpenProfileDeepLinkLauncher implements IDeepLinkLauncher {

    @Override
    public void launchDeepLink(DeepLink deepLink) {
        System.out.println( "------- Open profile -------" );
        System.out.println( "ID: " + deepLink.getQueryParameters().get( "id" ) );
        System.out.println( "Alias: " + deepLink.getQueryParameters().get( "alias" ) );
        System.out.println( "Referral: " + deepLink.getDeepLinkReferral().getName() );
    }

}
