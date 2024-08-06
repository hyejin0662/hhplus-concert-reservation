package com.concert_reservation.api.domain.queue.model;

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
public class WaitingCount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long countId;

	@Column(nullable = false)
	private Long count;

	public static WaitingCount createDefault() {
		return new WaitingCount(1L, 0L);
	}

	public void incrementCount() {
		this.count++;
	}

	public void decrementCount() {
		this.count--;
	}
}
