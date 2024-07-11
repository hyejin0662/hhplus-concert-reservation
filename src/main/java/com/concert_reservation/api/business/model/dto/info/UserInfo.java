package com.concert_reservation.api.business.model.dto.info;

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
public class UserInfo {
    private String userId;
    private String name;
    private String email;
    private String phoneNumber;
    private Long balance;


    public static UserInfo from(User user) {
        return UserInfo.builder()
            .userId(user.getUserId())
            .name(user.getName())
            .email(user.getEmail())
            .phoneNumber(user.getPhoneNumber())
            .balance(user.getBalance())
            .build();
    }

    public User toEntity() {
        return User.builder()
            .userId(this.userId)
            .name(this.name)
            .email(this.email)
            .phoneNumber(this.phoneNumber)
            .balance(this.balance)
            .build();
    }
}
