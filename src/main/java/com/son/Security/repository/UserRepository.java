package com.son.Security.repository;

import com.son.Security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Data JPA에서 제공하지 않는 메서드는 따로 정의해준다
    Optional<User> findByUserId(String userId);
}