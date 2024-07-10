package com.concert_reservation.api.application.dto.response;

import com.concert_reservation.api.business.model.dto.info.UserInfo;
import com.concert_reservation.api.business.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
        return UserResponse.builder()
            .userId(userInfo.getUserId())
            .name(userInfo.getName())
            .phoneNumber(userInfo.getPhoneNumber())
            .email(userInfo.getEmail())
            .balance(userInfo.getBalance())
            .build();
    }

    public User toEntity() {
        return User.builder()
            .userId(this.userId)
            .name(this.name)
            .phoneNumber(this.phoneNumber)
            .email(this.email)
            .build();
    }
}
