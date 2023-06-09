package com.nhnacademy.account_api_1.repository;

import com.nhnacademy.account_api_1.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
