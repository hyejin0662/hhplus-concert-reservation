package com.concert_reservation.api.business.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
  private Long concertOptionId;

  @Column(nullable = false)
  private Long concertId;

  @Column(nullable = false)
  private String singerName;

  @Column(nullable = false)
  private LocalDateTime concertDate;

  @Column(nullable = false)
  private Long capacity;

  @Column(nullable = false)
  private String location;

  @OneToMany(mappedBy = "seatId")
  private List<Seat> seats;

  public void updateCapacity(Long capacity) {
    this.capacity = capacity;
  }

  public boolean isAfter(LocalDateTime localDateTime) {
    return this.concertDate!=null && this.concertDate.isAfter(localDateTime);
  }
}