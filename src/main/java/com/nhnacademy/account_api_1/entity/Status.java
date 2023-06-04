package com.nhnacademy.account_api_1.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "status_name")
    @Enumerated(EnumType.STRING)
    private StatusName statusName;

    @OneToMany(mappedBy = "status")
    private List<User> users;

    public enum StatusName{
        JOINED("가입"), DORMANT("휴면"), CANCELED("탈퇴");
        @Getter
        private String statusName;
        StatusName(String statusName){
            this.statusName = statusName;
        }
    }
}
