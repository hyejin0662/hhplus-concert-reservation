package com.concert_reservation.api.application.user;

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
public class UserInfo {
    private String userId;
    private String name;
    private String email;
    private String phoneNumber;
    private Long balance;


    public static UserInfo from(User user) {
        return DtoConverter.convert(user,UserInfo.class);
    }

}
