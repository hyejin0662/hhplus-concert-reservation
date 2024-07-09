package com.concert_reservation.api.application.dto.response;

import com.concert_reservation.api.business.model.dto.command.UserCommand;
import com.concert_reservation.api.business.model.dto.info.UserInfo;
import com.concert_reservation.api.business.model.entity.User;

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

    public static UserResponse from(User user) {
        return UserResponse.builder()
            .userId(user.getUserId())
            .name(user.getName())
            .phoneNumber(user.getPhoneNumber())
            .email(user.getEmail())
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