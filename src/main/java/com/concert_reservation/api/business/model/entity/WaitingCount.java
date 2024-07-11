package com.concert_reservation.api.business.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "WaitingCount")
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
