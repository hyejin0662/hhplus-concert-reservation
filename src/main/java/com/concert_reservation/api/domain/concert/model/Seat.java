package com.concert_reservation.api.domain.concert.model;

import com.concert_reservation.common.exception.CustomException;
import com.concert_reservation.common.type.GlobalResponseCode;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    @Column(nullable = false)
    private int seatNumber;

    @Column(nullable = false)
    private boolean isReserved; // 좌석에 status 값을 넣으면 안된다

    @Column(nullable = false)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_option_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT) )
    private ConcertOption concertOption;

    public void isReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }


    public void updateSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void updatePrice(int price) {
        this.price = price;
    }

    public boolean isNotReserved(){
        return !isReserved;
    }

    public void reserve() {
        if (isReserved) { // 이선좌
            throw new CustomException(GlobalResponseCode.ALREADY_RESERVED);
        }
        this.isReserved = true;
    }


}