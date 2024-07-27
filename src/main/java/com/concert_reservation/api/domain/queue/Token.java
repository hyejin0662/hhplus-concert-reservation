package com.concert_reservation.api.domain.queue;

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
import java.util.UUID;

import com.concert_reservation.api.domain.user.User;
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
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime waitingAt; // 토큰이 시작된 시간

    @Column(nullable = false)
    private LocalDateTime expirationAt;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private TokenStatus tokenStatus;

    @Column(nullable = false)
    private int waitingNumber;

    public static Token issueToken(TokenCommand.IssueToken command) {
        String tokenCode = UUID.randomUUID().toString();
        var expirationTime = LocalDateTime.now().plusMinutes(5);
        Token token = Token.builder()
            .user(command.user)
            .tokenId(Long.valueOf(tokenCode.replace("-", "").substring(0, 18)))
            .waitingAt(LocalDateTime.now())
            .expirationAt(expirationTime)
            .tokenStatus(TokenStatus.WAITING)
            .build();
        return token;
    }

    public Token active() {
        if (this.tokenStatus == TokenStatus.WAITING) {
            this.tokenStatus = TokenStatus.ACTIVE;
        }
        return this;
    }

    public void expire() {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(now.minusMinutes(5))) throw new RuntimeException("5분이 지나지 않았습니다.");
        if (this.tokenStatus != TokenStatus.ACTIVE) throw new RuntimeException("활성상태만 만료시킬수 있습니다.");
        this.tokenStatus = TokenStatus.EXPIRED;
    }

}