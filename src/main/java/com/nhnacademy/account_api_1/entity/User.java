package com.nhnacademy.account_api_1.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index_id")
    private Long indexId;

    @Column(name = "user_id")
    private String userId;

    @Setter
    private String password;

    @Setter
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

}
