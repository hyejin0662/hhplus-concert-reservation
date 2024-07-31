package com.concert_reservation.common.type;

public enum RedisKeys {
	QUEUE_KEY("waitingQueue"),
	PROCESSING_KEY("processingQueue"),
	TOKEN_KEY_PREFIX("token:"),
	COUNTER_KEY("activeTokenCounter");

	private final String key;

	RedisKeys(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}
