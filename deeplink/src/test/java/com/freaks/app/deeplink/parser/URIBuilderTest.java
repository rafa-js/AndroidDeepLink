package com.freaks.app.deeplink.parser;

import com.freaks.app.deeplink.URIBuilder;

import org.junit.Assert;
import org.junit.Test;

public class URIBuilderTest {

    @Test
    public void testBuildEmpty() throws Exception {
        URIBuilder uriBuilder = URIBuilder.create()
                .setSchema( "http" )
                .setHost( "hackers.com" )
                .setPath( "posts" )
                .addQueryParameter( "tag", "android" )
                .addQueryParameter( "page", "3" );

        String uri = uriBuilder.buildAsString();

        Assert.assertEquals( "http://hackers.com/posts?tag=android&page=3", uri );
    }

    @Test
    public void testBuildWithoutQueryParameters() throws Exception {
        URIBuilder uriBuilder = URIBuilder.create()
                .setSchema( "http" )
                .setHost( "hackers.com" )
                .setPath( "posts" );

        String uri = uriBuilder.buildAsString();

        Assert.assertEquals( "http://hackers.com/posts", uri );
    }

    @Test
    public void testBuildWithoutSchema() throws Exception {
        URIBuilder uriBuilder = URIBuilder.create()
                .setHost( "hackers.com" );

        try {
            uriBuilder.checkArguments();
            Assert.fail( "An exception should be thrown" );
        } catch ( IllegalArgumentException ignored ) {
        }
    }

    @Test
    public void testBuildWithoutHost() throws Exception {
        URIBuilder uriBuilder = URIBuilder.create()
                .setSchema( "http" );

        try {
            uriBuilder.checkArguments();
            Assert.fail( "An exception should be thrown" );
        } catch ( IllegalArgumentException ignored ) {
        }
    }

    @Test
    public void testBuildWithoutPath() throws Exception {
        URIBuilder uriBuilder = URIBuilder.create()
                .setSchema( "http" )
                .setHost( "hackers.com" );

        String uri = uriBuilder.buildAsString();

        Assert.assertEquals( "http://hackers.com", uri );
    }

    @Test
    public void testBuildFromSchemaAndHost() throws Exception {
        URIBuilder uriBuilder = URIBuilder.fromURI( "http://hackers.com/posts" )
                .addQueryParameter( "tag", "android" )
                .addQueryParameter( "page", "3" );

        String uri = uriBuilder.buildAsString();

        Assert.assertEquals( "http://hackers.com/posts?tag=android&page=3", uri );
    }


}