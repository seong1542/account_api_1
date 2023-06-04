package com.nhnacademy.account_api_1.repository;

import com.nhnacademy.account_api_1.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
