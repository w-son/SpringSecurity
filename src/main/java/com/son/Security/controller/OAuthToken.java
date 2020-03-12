package com.son.Security.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OAuthToken {
    /** gson 매핑 후 리턴할 클래스 **/
    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
    private String scope;

}