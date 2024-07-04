package com.concert_reservation.api.business.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.OneToMany;
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
  private String name;
  private LocalDateTime date;

  @OneToMany(mappedBy = "concert")
  private List<Seat> seats;
}