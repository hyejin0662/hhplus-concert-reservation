package com.concert_reservation.api.interfaces.controller.user.dto;

import com.concert_reservation.api.application.user.UserInfo;
import com.concert_reservation.api.domain.user.model.User;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String userId;
    private String name;
    private String phoneNumber;
    private String email;
    private Long balance;


    public static UserResponse from(String userId) {
        return UserResponse.builder()
            .userId(userId)
            .build();
    }
    public static UserResponse from(UserInfo userInfo) {
        return DtoConverter.convert(userInfo,UserResponse.class);
    }

    public User toEntity() {
        return DtoConverter.convert(this, User.class);
    }
}
