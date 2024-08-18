package com.concert_reservation.api.domain.common;

import com.concert_reservation.api.domain.point.PointEventListener;

public interface DataPlatformClient {
	void sendResult(PointEventListener.DataPlatformRequest from);
}
