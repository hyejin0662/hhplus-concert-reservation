package com.concert_reservation.api.business.model.dto.command;

import com.concert_reservation.api.business.model.entity.User;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserCommand {
    private String name;
    private String email;

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .build();
    }
}
