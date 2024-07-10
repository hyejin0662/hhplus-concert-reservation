
package com.concert_reservation.api.business.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.concert_reservation.common.type.BookingStatus;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Booking")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;


    @Column(nullable = false)
    private String userId;


    @Column(nullable = false)
    private Long seatId;


    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus bookingStatus;

    @Column(nullable = false)
    private LocalDateTime bookingTime;

    public static Booking createBooking(String userId, Long seatId, LocalDateTime bookingTime, BookingStatus bookingStatus) {
        return Booking.builder()
            .userId(userId)
            .seatId(seatId)
            .bookingTime(bookingTime)
            .bookingStatus(bookingStatus)
            .build();
    }

    public void updateBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}