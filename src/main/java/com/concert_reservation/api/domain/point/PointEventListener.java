package com.concert_reservation.api.domain.point;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.concert_reservation.api.domain.common.DataPlatformClient;
import com.concert_reservation.api.domain.point.event.PointInternalEvent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PointEventListener {

	private final DataPlatformClient dataPlatformClient;


	@Async
	@TransactionalEventListener
	public void handlePaymentResultEvent(PointInternalEvent pointInternalEvent){
		dataPlatformClient.sendResult(DataPlatformRequest.from(pointInternalEvent));
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class DataPlatformRequest {
		private String sampleField;

		public static DataPlatformRequest from(PointInternalEvent pointInternalEvent) {
			return DataPlatformRequest.builder().sampleField(pointInternalEvent.getPayload()).build();
		}
	}
}
