package com.concert_reservation.api.domain.point.model;

import lombok.Getter;

@Getter
public enum OutboxEventStatus {
	INIT, PUBLISHED, REPUBLISHED;
}
