package com.freaks.app.deeplink.parser;

import com.freaks.app.deeplink.DeepLink;
import com.freaks.app.deeplink.DeepLinkReferral;
import com.freaks.app.deeplink.IDeepLinkParser;
import com.freaks.app.deeplink.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
            deepLink.setQueryParameters( parseQueryParameters( path ) );
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
        if ( hasQueryParameters( path ) ) {
            path = path.split( "\\?" )[0];
        }
        return path.split( StringUtils.SLASH );
    }

    private Map<String, String> parseQueryParameters(String path) {
        Map<String, String> parameters = new HashMap<>();
        try {
            if ( hasQueryParameters( path ) ) {
                String[] components = path.split( "\\?" );
                String lastComponent = components[1];
                parameters = extractQueryParameters( lastComponent );
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return parameters;
    }

    private DeepLinkReferral parseReferral(String url) {
        Map<String, String> parameters = parseQueryParameters( url );
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

    private boolean hasQueryParameters(String url) {
        return url.contains( StringUtils.QUESTION_MARK );
    }

    private Map<String, String> extractQueryParameters(String query) throws MalformedURLException {
        Map<String, String> parameters = new HashMap<>();
        List<String> components = Arrays.asList( query.split( StringUtils.AMPERSAND ) );
        for ( String component : components ) {
            String[] pair = component.split( "=" );
            if ( pair.length != 2 ) {
                throw new MalformedURLException();
            }
            try {
                parameters.put( pair[0], URLDecoder.decode( pair[1], "UTF-8" ) );
            } catch ( UnsupportedEncodingException e ) {
                e.printStackTrace();
            }
        }
        return parameters;
    }

}
