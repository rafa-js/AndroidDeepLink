package com.freaks.app.deeplink.parser;

import com.freaks.app.deeplink.DeepLink;
import com.freaks.app.deeplink.DeepLinkReferral;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DefaultDeepLinkParserTest {

    private DefaultDeepLinkParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new DefaultDeepLinkParser( "http", "google.com" );
    }

    @Test
    public void testParseDeepLinkFromUrl() throws Exception {
        String url = "http://google.com/eat?food=pizza";

        DeepLink deepLink = parser.getDeepLinkFromUrl( url );

        Assert.assertEquals( url, deepLink.getUrl() );
        Assert.assertArrayEquals( new String[]{"eat"}, deepLink.getPathComponents() );
        Assert.assertEquals( "/eat", deepLink.getAction() );
        Assert.assertEquals( DeepLinkReferral.UNKNOWN, deepLink.getDeepLinkReferral() );
        Assert.assertTrue( deepLink.getQueryParameters().containsKey( "food" ) );
        Assert.assertEquals( "pizza", deepLink.getQueryParameters().get( "food" ) );
    }

    @Test
    public void testParseDeepLinkFromUrlWithReferral() throws Exception {
        String url = "http://google.com/eat?food=pizza&rf=4";

        DeepLink deepLink = parser.getDeepLinkFromUrl( url );

        Assert.assertEquals( url, deepLink.getUrl() );
        Assert.assertArrayEquals( new String[]{"eat"}, deepLink.getPathComponents() );
        Assert.assertEquals( "/eat", deepLink.getAction() );
        Assert.assertEquals( DeepLinkReferral.TWITTER, deepLink.getDeepLinkReferral() );
        Assert.assertTrue( deepLink.getQueryParameters().containsKey( "food" ) );
        Assert.assertEquals( "pizza", deepLink.getQueryParameters().get( "food" ) );
    }

    @Test
    public void testParseDeepLinkFromDifferentSchema() throws Exception {
        String url = "ws://google.com/eat?food=pizza&rf=2";

        DeepLink deepLink = parser.getDeepLinkFromUrl( url );

        Assert.assertEquals( DeepLink.EMPTY, deepLink );
    }

    @Test
    public void testParseDeepLinkFromDifferentHost() throws Exception {
        String url = "http://yahoo.com/eat?food=pizza";

        DeepLink deepLink = parser.getDeepLinkFromUrl( url );

        Assert.assertEquals( DeepLink.EMPTY, deepLink );
    }

}