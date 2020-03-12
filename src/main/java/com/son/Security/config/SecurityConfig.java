package com.son.Security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

    /**
     1. /oauth/** : Authorization 서버 세팅시 자동으로 생성되는 주소
     2. /oauth2/** : 사용자 설정 주소
     3. /h2-console/* : h2 관련 주소
     1,2,3 은 누구나에게 접근 가능하도록 설정 되어 있다
     원치 않는다면 permitAll() 이 아닌
     hasRole("USER") 등 으로 권한을 나눈다
     **/
    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests().antMatchers("/oauth/**", "/oauth/token", "/oauth2/**", "/h2-console/*").permitAll()
                .and()
                .formLogin().and()
                .httpBasic();
    }

}
