package com.freaks.app.deeplink.parser;

import com.freaks.app.deeplink.DeepLink;
import com.freaks.app.deeplink.DeepLinkReferral;
import com.freaks.app.deeplink.IDeepLinkParser;
import com.freaks.app.deeplink.util.StringUtils;
import com.freaks.app.deeplink.util.URIUtils;

import java.net.URLDecoder;
import java.util.Map;

public class DefaultDeepLinkParser implements IDeepLinkParser {

    public static final String REFERRAL_PARAMETER_NAME = "rf";

    private String schema;
    private String host;

    public DefaultDeepLinkParser(String schema, String host) {
        this.schema = schema;
        this.host = host;
    }

    @Override
    public DeepLink getDeepLinkFromUrl(String url) {
        String expectedPrefix = schema + "://" + host;
        if ( !url.startsWith( expectedPrefix ) ) {
            return DeepLink.EMPTY;
        }
        String path = url.replace( expectedPrefix, StringUtils.EMPTY );
        return parseDeepLinkFromPath( url, path );
    }

    private DeepLink parseDeepLinkFromPath(String url, String path) {
        try {
            path = URLDecoder.decode( path, "UTF-8" );
            DeepLink deepLink = new DeepLink();
            deepLink.setUrl( url );
            deepLink.setAction( parseAction( path ) );
            deepLink.setDeepLinkReferral( parseReferral( url ) );
            deepLink.setPathComponents( parseArguments( path ) );
            deepLink.setQueryParameters( URIUtils.parseQueryParameters( path ) );
            return deepLink;
        } catch ( Exception e ) {
            e.printStackTrace();
            return DeepLink.EMPTY;
        }
    }

    private String parseAction(String path) {
        int index = path.indexOf( StringUtils.QUESTION_MARK );
        if ( index != -1 ) {
            return path.substring( 0, index );
        }
        return path;
    }

    private String[] parseArguments(String path) {
        while ( path.contains( "//" ) ) {
            path = path.replace( "//", "/" );
        }
        if ( path.startsWith( "/" ) ) {
            path = path.substring( 1 );
        }
        if ( URIUtils.hasQueryParameters( path ) ) {
            path = path.split( "\\?" )[0];
        }
        return path.split( StringUtils.SLASH );
    }

    private DeepLinkReferral parseReferral(String url) {
        Map<String, String> parameters = URIUtils.parseQueryParameters( url );
        if ( parameters.containsKey( REFERRAL_PARAMETER_NAME ) ) {
            try {
                int code = Integer.parseInt( parameters.get( REFERRAL_PARAMETER_NAME ) );
                return DeepLinkReferral.getFromCode( code );
            } catch ( Exception ex ) {
                ex.printStackTrace();
                return DeepLinkReferral.UNKNOWN;
            }
        }
        return DeepLinkReferral.UNKNOWN;
    }

}
