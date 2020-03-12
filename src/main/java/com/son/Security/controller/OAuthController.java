package com.son.Security.controller;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth2")
public class OAuthController {

    private final Gson gson;
    private final RestTemplate restTemplate;

    /**
     /oauth/authorize 의 요청이후로
     /oauth2/redirect 로 리다이렉트될 요청을 처리
     **/
    // 인증 토큰 생성
    @GetMapping("/redirect")
    public OAuthToken redirect(@RequestParam String code) {

        final String RequestUri = "http://localhost:8080/oauth/token";
        final String RedirectUri = "http://localhost:8080/oauth2/redirect";

        // credentials 설정
        String credentials = "testClientId:testClientSecret";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        // Query String 설정
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", RedirectUri);

        // Header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + encodedCredentials);

        // Request & Response
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(RequestUri, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            // Response Mapping
            return gson.fromJson(response.getBody(), OAuthToken.class);
        }
        return null;
    }

    // 인증 토큰 갱신
    @GetMapping("/refresh")
    public OAuthToken refreshToken(@RequestParam String refreshToken) {

        final String RequestUri = "http://localhost:8080/oauth/token";

        System.out.println(refreshToken);

        // credentials 설정
        String credentials = "testClientId:testClientSecret";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        // Query String 설정
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("refresh_token", refreshToken);
        params.add("grant_type", "refresh_token");

        // Header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Authorization", "Basic " + encodedCredentials);

        // Request & Response
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(RequestUri, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            // Response Mapping
            return gson.fromJson(response.getBody(), OAuthToken.class);
        }
        return null;

    }

}
