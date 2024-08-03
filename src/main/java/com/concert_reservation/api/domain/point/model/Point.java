
package com.concert_reservation.api.domain.point.model;

import com.concert_reservation.api.domain.user.model.User;
import com.concert_reservation.common.exception.CustomException;
import com.concert_reservation.common.type.GlobalResponseCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private LocalDateTime paymentTime;

    @Column(nullable = false)
    private String paymentMethod;

    @Version
    private Long version;

    public void charge(Long amount) {
        this.amount += amount;
    }

    public void pay(Long amount) {

        if(this.amount < amount){
            throw new CustomException(GlobalResponseCode.INSUFFICIENT_POINT);
        }

        this.amount -= amount;
    }


    public void cancel() {
        if (this.amount <= 0) {
            throw new CustomException(GlobalResponseCode.INSUFFICIENT_POINT);
        }

        // 환불 로직 (예: 환불된 금액을 사용자의 잔액에 더함)
        this.amount += this.amount;

        // 결제 금액을 0으로 초기화
        this.amount = 0L;
    }
}