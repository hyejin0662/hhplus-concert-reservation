package com.concert_reservation.api.business.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class ConcertOption {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long optionId;
  private String name;
  private String value;

  @ManyToOne
  @JoinColumn(name = "concert_id")
  private Concert concert;
}