package com.concert_reservation.api.business.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

import com.concert_reservation.api.business.model.domain.WaitingToken;
import com.concert_reservation.api.business.model.dto.info.TokenInfo;
import com.concert_reservation.api.business.model.dto.info.TokenValidateInfo;
import com.concert_reservation.common.type.TokenStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Token  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // private String userId;  // redis 사용시
    // private String tokenValue;  // redis 사용시

    @Column(nullable = false)
    private LocalDateTime waitingAt; // 토큰이 시작된 시간

    @Column(nullable = false)
    private LocalDateTime expirationAt;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private TokenStatus tokenStatus;

    @Column(nullable = false)
    private int waitingNumber;

    public Token(String tokenId, String userId, TokenStatus status, LocalDateTime expirationAt, LocalDateTime waitingAt) {
        this.tokenId = tokenId != null ? Long.valueOf(tokenId) : null;
        this.user = new User();
        user.setUserId(userId);
        this.tokenStatus = status;
        this.expirationAt = expirationAt;
        this.waitingAt = waitingAt;
    }

    public Token markAsProcessing() {
        if (this.tokenStatus == TokenStatus.WAITING) {
            this.tokenStatus = TokenStatus.PROCESSING;
        }
        return this;
    }

    public void doExpire() {
        this.tokenStatus = TokenStatus.EXPIRED;
    }

    public boolean isExpired() {
        return this.expirationAt.isBefore(LocalDateTime.now());
    }

    public boolean isProcessing() {
        return this.tokenStatus == TokenStatus.PROCESSING;
    }

    public TokenInfo toTokenInfoWithWaitingNumber(int waitingNumber) {
        return TokenInfo.builder()
            .tokenId(this.tokenId)
            .userId(this.user.getUserId())
            .expirationAt(this.expirationAt)
            .tokenStatus(this.tokenStatus)
            .waitingNumber(waitingNumber)
            .build();
    }

    public TokenValidateInfo toTokenValidateInfo() {
        return TokenValidateInfo.builder()
            .tokenId(this.tokenId)
            .userId(this.user.getUserId())
            .expirationAt(this.expirationAt)
            .tokenStatus(this.tokenStatus)
            .build();
    }
}