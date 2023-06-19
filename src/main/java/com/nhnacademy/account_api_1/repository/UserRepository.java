package com.nhnacademy.account_api_1.repository;

import com.nhnacademy.account_api_1.entity.User;
import com.nhnacademy.account_api_1.response.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom{
    boolean existsByEmail(String email);
    boolean existsByUserId(String userId);


    Optional<User> findUserByUserId(String id);
}
