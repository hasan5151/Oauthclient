package com.huzzy.oauth_client;

import android.content.Context;

import com.huzzy.oauth_client.Definition.Defs;
import com.huzzy.oauth_client.Function.Authorization;
import com.huzzy.oauth_client.Function.EnumFunc;
import com.huzzy.oauth_client.Model.Getcode;

import java.util.HashMap;
import java.util.Map;

public class OauthSmart {

    public static baseUrl newBuilder(Context context) {
        return new Steps(context);
    }

    private OauthSmart() { }

    public static interface baseUrl{
        clientId baseUrl(@Defs.BaseUrl String baseUrl);
    }

    public static interface clientId {
        redirectUri clientId(String clientId);
    }

    public static interface redirectUri {
        responseType redirectUri(String redirectUri);
    }



    public static interface responseType {
        /**
         * @param type "code" or "token"
         *
         * */
        scope responseType(@Defs.responseType String type);
     }


    public static interface scope {
        stateCode scope(String scope);
        stateCode scope (Enum... scope);
        build stateCode(String state);
        void build(Getcode authCode);
    }

    public static interface stateCode {
        build stateCode(String state);
        void build(Getcode authCode);
    }


    public static interface build {
        void build(Getcode authCode);
    }

    private static class Steps implements baseUrl,scope,clientId,redirectUri,build,stateCode,responseType{
        private String baseUrl;
        private String redirectUri;
        private String stateCode;
        private String clientId;
        private String scope;
        private String response_type;
        private Context context;

        private Map<String,Object> oauthMap = new HashMap<>();

        public Steps(Context context) {
            this.context=context;
        }

        @Override
        public clientId baseUrl(@Defs.BaseUrl String baseUrl) {
            this.baseUrl=baseUrl;
            return this;
        }

        @Override
        public redirectUri clientId(String clientId) {
            this.clientId=clientId;
            oauthMap.put("client_id",clientId);
            return this;
        }

        @Override
        public responseType redirectUri(String redirectUri) {
            this.redirectUri = redirectUri;
            oauthMap.put("redirect_uri",redirectUri);
            return this;
        }

        @Override
        public stateCode scope(String scope) {
            this.scope=scope;
            oauthMap.put("scope",scope);
            return this;
        }

        @Override
        public stateCode scope(Enum... scope) {
            String scopeStr = EnumFunc.toString(scope);
            this.scope=scopeStr;
            oauthMap.put("scope",scopeStr);
            return this;
        }

        @Override
        public void build(Getcode authCode) {
                new Authorization().get(authCode, oauthMap, baseUrl, context);
        }

        @Override
        public build stateCode(String state) {
            this.stateCode = state;
            oauthMap.put("state",state);
            return this;
        }

        @Override
        public scope responseType(@Defs.responseType  String type) {
            this.response_type=type;
            oauthMap.put("response_type",type);
            return this;
        }

    }
}
