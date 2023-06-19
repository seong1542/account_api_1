package com.nhnacademy.account_api_1.repository;

import com.nhnacademy.account_api_1.entity.QUser;
import com.nhnacademy.account_api_1.entity.User;
import com.nhnacademy.account_api_1.response.QUserResponse;
import com.nhnacademy.account_api_1.response.UserResponse;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {
    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public List<UserResponse> getUserResponseList() {
        QUser user = QUser.user;
        return from(user)
                .where(user.status().statusId.eq(1))
                .select(new QUserResponse(user.userId,
                        user.password,
                        user.status().statusName.stringValue(),
                        user.email))
                .fetch();
    }

    @Override
    public Optional<UserResponse> getUserResponseByUserId(String id) {
        QUser user = QUser.user;

        return Optional.of(from(user)
                .where(user.userId.eq(id))
                .select(new QUserResponse(user.userId,
                        user.password,
                        user.status().statusName.stringValue(),
                        user.email))
                .fetchFirst());
    }

    @Override
    public Optional<UserResponse> findByEmail(String email) {
        QUser user = QUser.user;
        return Optional.of(from(user)
                .where(user.email.eq(email))
                .select(new QUserResponse(user.userId,
                        user.password,
                        user.status().statusName.stringValue(),
                        user.email))
                .fetchFirst());
    }
}
