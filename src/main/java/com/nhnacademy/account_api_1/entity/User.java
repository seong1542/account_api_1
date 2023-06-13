package com.nhnacademy.account_api_1.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_index")
    private Long indexId;

    @Column(name = "user_id")
    private String userId;

    private String password;


    private String email;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;

    public User(String userId, String password, String email, Status status){
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.status = status;
    }
    public void updateUser(String password, String email){
        this.password = password;
        this.email = email;
    }

}
