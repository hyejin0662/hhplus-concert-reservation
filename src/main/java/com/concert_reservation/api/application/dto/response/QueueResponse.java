package com.concert_reservation.api.application.dto.response;

import java.time.LocalDateTime;

import com.concert_reservation.common.type.QueueStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QueueResponse {
	private String queueToken;
	private int position;
	private LocalDateTime issueTime;
	private LocalDateTime expirationTime;
	private QueueStatus queueStatus;
}
