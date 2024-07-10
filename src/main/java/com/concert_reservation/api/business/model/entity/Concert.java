package com.concert_reservation.api.business.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Concert")
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

  @Column(nullable = false)
  private LocalDateTime concertDate;
}