package com.concert_reservation.api.business.model.dto.info;

import java.util.List;
import java.util.stream.Collectors;

import com.concert_reservation.api.business.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
    private List<PointInfo> points;
    private List<BookingInfo> bookings;
    private List<TempReservationInfo> tempReservations;
    private List<TokenInfo> tokens;
    private List<WaitingCountInfo> waitingCounts;

    public static UserInfo from(User user) {
        return UserInfo.builder()
            .userId(user.getUserId())
            .name(user.getName())
            .email(user.getEmail())
            .phoneNumber(user.getPhoneNumber())
            .balance(user.getBalance())
            .points(user.getPoints().stream().map(PointInfo::from).collect(Collectors.toList()))
            .bookings(user.getBookings().stream().map(BookingInfo::from).collect(Collectors.toList()))
            .tempReservations(user.getTempReservations().stream().map(TempReservationInfo::from).collect(Collectors.toList()))
            .tokens(user.getTokens().stream().map(TokenInfo::from).collect(Collectors.toList()))
            .waitingCounts(user.getWaitingCounts().stream().map(WaitingCountInfo::from).collect(Collectors.toList()))
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