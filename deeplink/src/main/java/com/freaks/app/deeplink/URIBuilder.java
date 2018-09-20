package com.freaks.app.deeplink;

import android.net.Uri;

import com.freaks.app.deeplink.util.StringUtils;
import com.freaks.app.deeplink.util.URIUtils;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class URIBuilder {

    private String schema;
    private String host;
    private String path;
    private Map<String, String> queryParameters;

    public static URIBuilder fromURI(String uri) throws MalformedURLException {
        String[] components = uri.split( "://" );
        String schema = components[0];
        String host;
        String path;
        if ( components[1].contains( StringUtils.SLASH ) ) {
            int firstSlashIndex = components[1].indexOf( StringUtils.SLASH );
            host = components[1].substring( 0, firstSlashIndex );
            path = components[1].substring( firstSlashIndex );
        } else {
            host = components[1];
            path = StringUtils.EMPTY;
        }
        if ( URIUtils.hasQueryParameters( path ) ) {
            path = path.substring( 0, path.indexOf( StringUtils.QUESTION_MARK ) );
        }
        Map<String, String> queryParameters = URIUtils.extractQueryParameters( path );
        return new URIBuilder( schema, host, path, queryParameters );
    }

    public static URIBuilder create() throws MalformedURLException {
        return new URIBuilder( StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, new HashMap<>() );
    }

    public URIBuilder(String schema, String host, String path, Map<String, String> queryParameters) {
        this.schema = schema;
        this.host = host;
        this.path = path;
        this.queryParameters = queryParameters;
    }

    public URIBuilder setSchema(String schema) {
        this.schema = schema;
        return this;
    }

    public URIBuilder setHost(String host) {
        this.host = host;
        return this;
    }

    public URIBuilder setPath(String path) {
        if ( !path.startsWith( StringUtils.SLASH ) ) {
            this.path = StringUtils.SLASH + path;
        } else {
            this.path = path;
        }
        return this;
    }

    public URIBuilder addQueryParameter(String name, String value) {
        queryParameters.put( name, value );
        return this;
    }

    public void checkArguments() throws IllegalArgumentException {
        if ( StringUtils.isEmpty( schema ) ) {
            throw new IllegalArgumentException( "schema is required" );
        }
        if ( StringUtils.isEmpty( host ) ) {
            throw new IllegalArgumentException( "host is required" );
        }
    }

    public String buildAsString() {
        checkArguments();
        return schema + "://" + host + path + URIUtils.encodeQueryParameters( queryParameters );
    }

    public Uri build() {
        return Uri.parse( buildAsString() );
    }


}
