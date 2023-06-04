package com.nhnacademy.account_api_1.repository;

import com.nhnacademy.account_api_1.entity.QStatus;
import com.nhnacademy.account_api_1.entity.QUser;
import com.nhnacademy.account_api_1.entity.Status;
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
                .select(new QUserResponse(user.userId, user.email))
                .fetch();
    }

    @Override
    public UserResponse getUserResponseByIndexId(Long id) {
        QUser user = QUser.user;

        return from(user)
                .where(user.indexId.eq(id))
                .select(new QUserResponse(user.userId, user.email))
                .fetchOne();
    }
}
