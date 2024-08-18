package com.concert_reservation.api.infrastructure.common;

import org.springframework.stereotype.Component;

import com.concert_reservation.api.domain.common.DataPlatformClient;
import com.concert_reservation.api.domain.point.PointEventListener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataPlatformClientImpl implements DataPlatformClient {

	@Override
	public void sendResult(PointEventListener.DataPlatformRequest from) {
		try{
			Thread.sleep(1000);
			log.info("데이터 플랫폼 전송 완료");
		} catch (Exception e){
			log.info("데이터 플랫폼 전송 실패");
		}
	}
}
