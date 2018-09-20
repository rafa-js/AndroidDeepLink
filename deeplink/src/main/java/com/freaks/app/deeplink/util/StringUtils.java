package com.freaks.app.deeplink.util;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static final String EMPTY = "";
    public static final String SPACE = " ";
    public static final String DOT = ".";
    public static final String ELLIPSIS = "...";
    public static final String SLASH = "/";
    public static final String AMPERSAND = "&";
    public static final String QUESTION_MARK = "?";

    public static String totalTrim(String string) {
        if ( string == null ) {
            return null;
        }
        return string.replaceAll( "( )+", StringUtils.SPACE );
    }

    public static String fixNewLines(String string) {
        if ( string == null ) {
            return null;
        }
        return string.replace( "\\n", "\n" );
    }

    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }

    public static String getStringNullSafe(String s) {
        return getStringNullSafe( s, StringUtils.EMPTY );
    }

    public static String getStringNullSafe(String s, String emptyString) {
        if ( isEmpty( s ) )
            return emptyString;

        return s;
    }

    public static String getLongToStringNullSafe(Long l) {
        if ( l != null ) {
            return getStringNullSafe( l.toString() );
        }

        return "";
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile( expression, Pattern.CASE_INSENSITIVE );
        Matcher matcher = pattern.matcher( inputStr );
        if ( matcher.matches() ) {
            isValid = true;
        }
        return isValid;
    }

    public static final boolean isValidPhoneNumber(String target) {
        if ( target == null || StringUtils.isEmpty( target ) ) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher( target ).matches();
        }
    }

    public static final boolean isValidWeb(String target) {
        if ( target == null || StringUtils.isEmpty( target ) ) {
            return false;
        } else {
            return android.util.Patterns.WEB_URL.matcher( target ).matches();
        }
    }

    public static final String ellipsize(String string, int size) {
        if ( string.length() > size ) {
            return string.substring( 0, size ) + ELLIPSIS;
        }
        return string;
    }

    public static final String capitalizeString(String str) {
        if ( isEmpty( str ) ) {
            return str;
        }
        str = str.trim();
        return str.substring( 0, 1 ).toUpperCase() + str.substring( 1 );
    }

    public static final String buildQueryString(Map<?, ?> map) {
        String query = StringUtils.EMPTY;
        if ( map.size() == 0 ) {
            return query;
        }
        for ( Object k : map.keySet() ) {
            query += k.toString() + "=" + map.get( k ) + "&";
        }
        return query.substring( 0, query.length() - 1 );
    }

    public static final String join(String separator, List<String> items) {
        StringBuilder builder = new StringBuilder();
        for ( int i = 0; i < items.size(); i++ ) {
            if ( i != 0 ) {
                builder.append( separator );
            }
            builder.append( items.get( i ) );
        }
        return builder.toString();
    }
}