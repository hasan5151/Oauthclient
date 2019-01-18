package com.huzzy.oauth_client.Definition;

import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.SOURCE;

public class Defs {
    @Retention(SOURCE)
    @StringDef({
            Twitch,
            Facebook,
            Google,
            Pinterest
    })
    public @interface BaseUrl {}

    @Retention(SOURCE)
    @StringDef({
            code,
            token
    })
    public @interface responseType { }

    public static final String code = "code";
    public static final String token = "token";

    public static final String Twitch = "https://id.twitch.tv/oauth2/authorize";
    public static final String Facebook = "https://www.facebook.com/v3.2/dialog/oauth";
    public static final String Pinterest = "https://api.pinterest.com/oauth/";
    public static final String Google = "https://accounts.google.com/o/oauth2/auth";
}
