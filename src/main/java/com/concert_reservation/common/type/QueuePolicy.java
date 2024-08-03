package com.concert_reservation.common.type;

public enum QueuePolicy {
    ACTIVE_USER_LIMIT(50),
    ACTIVE_TOKEN_EXPIRATION(600);

    long value;

    private QueuePolicy(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
