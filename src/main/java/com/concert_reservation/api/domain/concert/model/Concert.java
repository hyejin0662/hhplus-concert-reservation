package com.concert_reservation.api.domain.concert.model;

import com.concert_reservation.api.application.concert.ConcertCommand;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Concert {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long concertId;

  @Column(nullable = false)
  private String concertName;

	public Concert update(ConcertCommand concertCommand) {
		return Concert.builder()
			.concertId(concertId)
			.concertName(concertCommand.getConcertName())
			.build();
	}
}