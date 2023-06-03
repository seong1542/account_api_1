package com.nhnacademy.account_api_1.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity(name = "Users")
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index_id")
    private Long indexId;

    @Column(name = "user_id")
    private String userId;

    private String password;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private Status status;
}
