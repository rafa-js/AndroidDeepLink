package com.freaks.app.deeplink.example;

import com.freaks.app.deeplink.DeepLink;
import com.freaks.app.deeplink.IDeepLinkLauncher;

public class OpenPostDeepLinkLauncher implements IDeepLinkLauncher {

    @Override
    public void launchDeepLink(DeepLink deepLink) {
        System.out.println( "------- Open Post -------" );
        System.out.println( "ID: " + deepLink.getQueryParameters().get( "id" ) );
        System.out.println( "Title: " + deepLink.getQueryParameters().get( "title" ) );
        System.out.println( "Referral: " + deepLink.getDeepLinkReferral().getName() );
    }

}
