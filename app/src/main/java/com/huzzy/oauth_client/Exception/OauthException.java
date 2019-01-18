package com.huzzy.oauth_client.Exception;

public class OauthException extends RuntimeException {
    String message;
    Throwable throwable;
    public OauthException() {
        super();
    }

    public OauthException(String message,Throwable throwable) {
        super(message,throwable);
        this.message = message;
        this.throwable= throwable;
    }
}
