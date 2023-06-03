package com.nhnacademy.account_api_1.repository;

import com.nhnacademy.account_api_1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
