package com.concert_reservation.api.interfaces.controller.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String userId;
    private String name;
    private String phoneNumber;
    private String email;
    private Long balance;
}
