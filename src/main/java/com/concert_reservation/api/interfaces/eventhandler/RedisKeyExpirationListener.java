package com.concert_reservation.api.interfaces.eventhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import com.concert_reservation.api.application.facade.TokenFacade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    private final TokenFacade tokenFacade;


    public RedisKeyExpirationListener(TokenFacade tokenFacade,
        RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
        this.tokenFacade = tokenFacade;
    }

    /**
     * 만료된 이벤트를 Redis로부터 수신합니다.
     * 이 리스너는 토큰이 만료될 때 해당 이벤트를 수신하여,
     * 처리열에서 카운터를 차감함으로써 현재 처리하고 있는 카운트가
     * 몇 개인지를 알 수 있게 해줍니다.
     *
     * @param message 만료된 키에 대한 Redis 메시지
     * @param pattern 수신된 패턴
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = new String(message.getBody());
        log.info("Key expired: {}", expiredKey);

        tokenFacade.decrementCounter();


    }
}
