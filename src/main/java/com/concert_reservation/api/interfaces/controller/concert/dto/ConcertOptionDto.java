package com.concert_reservation.api.interfaces.controller.concert.dto;

import java.time.LocalDateTime;

import com.concert_reservation.api.application.concert.ConcertOptionCommand;
import com.concert_reservation.api.application.concert.ConcertOptionInfo;
import com.concert_reservation.api.domain.concert.model.ConcertOption;
import com.concert_reservation.common.mapper.DtoConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class ConcertOptionDto {
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Request {
		private Long concertOptionId;
		private Long concertId;
		private String singerName;
		private LocalDateTime concertDate;
		private Long capacity;
		private String location;

		public ConcertOptionCommand toCommand() {
			return DtoConverter.convert(this,ConcertOptionCommand.class);
		}
	}
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Response {
		private Long concertOptionId;
		private Long concertId;
		private String singerName;
		private LocalDateTime concertDate;
		private Long capacity;
		private String location;

		public static Response from(
			ConcertOptionInfo concertOption) {
			return DtoConverter.convert(concertOption,
				Response.class);
		}

		public ConcertOption toEntity() {
			return DtoConverter.convert(this,ConcertOption.class);
		}
	}
}
