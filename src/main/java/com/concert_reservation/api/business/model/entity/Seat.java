package com.concert_reservation.api.business.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private boolean isReserved;

    @Column(nullable = false)
    private int price;



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


}