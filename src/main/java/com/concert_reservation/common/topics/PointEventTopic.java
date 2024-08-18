package com.concert_reservation.common.topics;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PointEventTopic {

	PAYMENT_RESULT("point-result", "결제 결과 토픽");

	private final String topicTitle;
	private final String description;
}
