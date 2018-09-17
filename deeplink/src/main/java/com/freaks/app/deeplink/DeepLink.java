package com.freaks.app.deeplink;

import com.freaks.app.deeplink.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DeepLink {

    public static final DeepLink EMPTY = new DeepLink();

    private String url;
    private String action;
    private DeepLinkReferral deepLinkReferral;
    private String[] pathComponents;
    private Map<String, String> queryParameters;

    public DeepLink() {
        super();
        this.url = StringUtils.EMPTY;
        this.action = StringUtils.EMPTY;
        this.deepLinkReferral = DeepLinkReferral.UNKNOWN;
        this.pathComponents = new String[]{};
        this.queryParameters = new HashMap<>();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public DeepLinkReferral getDeepLinkReferral() {
        return deepLinkReferral;
    }

    public void setDeepLinkReferral(DeepLinkReferral deepLinkReferral) {
        this.deepLinkReferral = deepLinkReferral;
    }

    public String[] getPathComponents() {
        return pathComponents;
    }

    public void setPathComponents(String[] pathComponents) {
        this.pathComponents = pathComponents;
    }

    public Map<String, String> getQueryParameters() {
        return queryParameters;
    }

    public void setQueryParameters(Map<String, String> queryParameters) {
        this.queryParameters = queryParameters;
    }

    @Override
    public String toString() {
        return "DeepLink{" +
                "url='" + url + '\'' +
                ", action='" + action + '\'' +
                ", referral='" + deepLinkReferral + '\'' +
                ", pathComponents=" + Arrays.toString( pathComponents ) +
                ", queryParameters=" + queryParameters +
                '}';
    }
}
