package com.concert_reservation.api.business.model.dto.command;

import com.concert_reservation.api.business.model.entity.User;
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
