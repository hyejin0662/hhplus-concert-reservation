package com.concert_reservation.api.domain.point.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
@Table(
	indexes = {
		@Index(name = "idx_status_createdAt", columnList = "status, createdAt"),
		@Index(name = "idx_key", columnList = "eventKey")
	}
)
public class OutboxEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String topic;

	private String eventKey;

	private String eventType;

	@Column(length = 5000)
	private String payload;

	@Enumerated(value = EnumType.STRING)
	private OutboxEventStatus status;


	@CreatedDate
	private LocalDateTime createdAt;

	public void confirmPublished() {
		this.status = OutboxEventStatus.PUBLISHED;
	}
}
