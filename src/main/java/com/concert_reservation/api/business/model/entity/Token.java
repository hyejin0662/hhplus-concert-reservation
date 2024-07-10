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

import java.time.LocalDateTime;

import com.concert_reservation.common.type.TokenStatus;

import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Token")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private LocalDateTime waitingAt;

    @Column(nullable = false)
    private LocalDateTime expirationAt;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private TokenStatus tokenStatus;

    @Column(nullable = false)
    private Long waitingCountId;

    public void updateTokenStatus(TokenStatus tokenStatus) {
        this.tokenStatus = tokenStatus;
    }

    public void extendExpirationAt(LocalDateTime newExpiration) {
        this.expirationAt = newExpiration;
    }
}