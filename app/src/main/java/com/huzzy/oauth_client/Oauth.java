package com.huzzy.oauth_client;

import android.content.Context;

import com.huzzy.oauth_client.Function.Authorization;
import com.huzzy.oauth_client.Function.EnumFunc;
import com.huzzy.oauth_client.Model.Getcode;

import java.util.HashMap;
import java.util.Map;

import static com.huzzy.oauth_client.Definition.Defs.*;

public class Oauth {
    private String baseUrl;
  /*private String redirectUri;
    private String stateCode;
    private String clientId;
    private String scope;
    private String response_type;*/
    private Context context;
    private Map<String,Object> oauthMap;

    public Oauth(Builder builder, Getcode authCode) {
        this.baseUrl=builder.baseUrl;
        this.oauthMap=builder.oauthMap;
        this.context=builder.context;
        //this.redirectUri=builder.redirectUri;
        //this.scope=builder.scope;
        //this.response_type=builder.response_type;
        //this.clientId=builder.clientId;
        //this.stateCode=builder.stateCode;
        new Authorization().get(authCode,oauthMap,baseUrl,context);
    }

    public static final class Builder {
   /*   private String redirectUri;
        private String clientId;
        private String stateCode;
        private String scope;
        private String response_type;
   */   private Context context;
        private String baseUrl;

        private Map<String,Object> oauthMap = new HashMap<>();

        public  Builder(Context context){
            this.context=context;
        }

        public Builder responseType(@responseType String  response_type){
            oauthMap.put("response_type",response_type);
            return this;
        }

        public Builder clientId(String clientId){
            oauthMap.put("client_id",clientId);
            return this;
        }

        public Builder scope (Enum... scope){
            String scopeStr = EnumFunc.toString(scope);
            oauthMap.put("scope",scopeStr);
            return this;
        }

        public Builder scope (String scope){
            oauthMap.put("scope",scope);
            return this;
        }

        private String enumToString(Enum... scope){
            StringBuilder scopes= new StringBuilder();
            for (int i=0;i<=scope.length-1;i++) {
                if (i == scope.length - 1)
                    scopes.append(scope[i]);
                else
                    scopes.append(scope[i]).append(" ");
            }
            return scopes.toString();
        }

        public Builder baseUrl(@BaseUrl String baseUrl){
            this.baseUrl=baseUrl;
            return this;
        }

        public Builder redirectUri(String redirectUri) {
            oauthMap.put("redirect_uri",redirectUri);
            return this;
        }

        public Builder stateCode(String stateCode) {
            oauthMap.put("state",stateCode);
            return this;
        }

        public Oauth build(Getcode authCode){
            return new Oauth(this,authCode);
        }
    }
}
