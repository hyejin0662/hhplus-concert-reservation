package com.concert_reservation.api.application.user;

import com.concert_reservation.api.domain.user.model.User;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCommand {
    private String userId;
    private String name;
    private String email;
    private String phoneNumber;
    private Long balance;

    public User toEntity() {
        return DtoConverter.convert(this, User.class);
    }
}
