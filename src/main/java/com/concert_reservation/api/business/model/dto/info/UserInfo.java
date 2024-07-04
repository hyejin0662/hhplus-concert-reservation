package com.concert_reservation.api.business.model.dto.info;

import com.concert_reservation.api.business.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {
    private String userId;
    private String name;
    private String email;

    public static UserInfo from(User user) {
        return UserInfo.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}