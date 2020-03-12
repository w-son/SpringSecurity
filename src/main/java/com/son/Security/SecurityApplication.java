package com.son.Security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@SpringBootApplication
public class SecurityApplication {

	/** 테스트 URI
	 http://localhost:8080/oauth/authorize?client_id=testClientId&redirect_uri=http://localhost:8080/oauth2/redirect&response_type=code&scope=read
	 http://localhost:8080/oauth2/refresh?refreshToken=
	 **/
	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

}