package com.freaks.app.deeplink;

import com.freaks.app.deeplink.parser.DefaultDeepLinkParser;

public class DeepLinkHandler {


    private IDeepLinkLauncherFactory deepLinkLauncherFactory;
    private IDeepLinkParser deepLinkParser;
    private IDeepLinkListener deepLinkListener;

    public static DeepLinkHandler createWithDefaultParser(IDeepLinkLauncherFactory deepLinkLauncherFactory,
                                                          String schema, String host) {
        return new DeepLinkHandler( deepLinkLauncherFactory, new DefaultDeepLinkParser( schema, host ) );
    }

    public static DeepLinkHandler createWithCustomParser(IDeepLinkLauncherFactory deepLinkLauncherFactory,
                                                         IDeepLinkParser deepLinkParser) {
        return new DeepLinkHandler( deepLinkLauncherFactory, deepLinkParser );
    }

    private DeepLinkHandler() {
    }

    public DeepLinkHandler(IDeepLinkLauncherFactory deepLinkLauncherFactory, IDeepLinkParser deepLinkParser) {
        this.deepLinkLauncherFactory = deepLinkLauncherFactory;
        this.deepLinkParser = deepLinkParser;
        this.deepLinkListener = deepLink -> {
        };
    }

    public void setDeepLinkListener(IDeepLinkListener deepLinkListener) {
        if ( deepLinkListener != null ) {
            this.deepLinkListener = deepLinkListener;
        }
    }

    public void handleLink(String url) {
        try {
            DeepLink deepLink = deepLinkParser.getDeepLinkFromUrl( url );
            IDeepLinkLauncher deepLinkLauncher = deepLinkLauncherFactory.getDeepLinkLauncher( deepLink );
            if ( deepLink != DeepLink.EMPTY && deepLinkLauncher != null ) {
                deepLinkLauncher.launchDeepLink( deepLink );
                deepLinkListener.onProcessDeepLink( deepLink );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public interface IDeepLinkListener {

        void onProcessDeepLink(DeepLink deepLink);

    }
}
