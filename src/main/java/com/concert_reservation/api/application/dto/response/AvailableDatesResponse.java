package com.concert_reservation.api.application.dto.response;

import java.time.LocalDateTime;

import com.concert_reservation.api.business.model.dto.info.AvailableDatesInfo;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AvailableDatesResponse {


	private Long concertOptionId;
	private Long concertId;
	private String singerName;
	private LocalDateTime concertDate;
	private Long capacity;
	private String location;

	public static AvailableDatesResponse from(AvailableDatesInfo info){
		return DtoConverter.convert(info, AvailableDatesResponse.class);
	}
}
