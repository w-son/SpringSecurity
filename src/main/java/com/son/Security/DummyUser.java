package com.son.Security;

import com.son.Security.domain.User;
import com.son.Security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class DummyUser {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        userRepository.save(User.builder()
                .userId("hello")
                .userPassword(passwordEncoder.encode("1234"))
                .userNickName("spring")
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
    }

}
