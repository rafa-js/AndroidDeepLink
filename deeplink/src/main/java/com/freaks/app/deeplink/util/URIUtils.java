package com.freaks.app.deeplink.util;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class URIUtils {

    public static boolean hasQueryParameters(String url) {
        return url.contains( StringUtils.QUESTION_MARK );
    }

    public static Map<String, String> parseQueryParameters(String path) {
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

    public static Map<String, String> extractQueryParameters(String query) throws MalformedURLException {
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