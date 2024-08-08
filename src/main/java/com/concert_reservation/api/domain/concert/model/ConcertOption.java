package com.concert_reservation.api.domain.concert.model;

import java.time.LocalDateTime;
import java.util.List;

import com.concert_reservation.api.application.concert.ConcertOptionCommand;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "concert_option", indexes = {
    @Index(name = "idx_concert_id", columnList = "concertId"),
    @Index(name = "idx_concert_date", columnList = "concertDate")
})
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


  public void updateCapacity(Long capacity) {
    this.capacity = capacity;
  }

  public boolean isAfter(LocalDateTime localDateTime) {
    return this.concertDate!=null && this.concertDate.isAfter(localDateTime);
  }

  public void updateFromCommand(ConcertOptionCommand command) {
    this.concertId = command.getConcertId();
    this.singerName = command.getSingerName();
    this.concertDate = command.getConcertDate();
    this.capacity = command.getCapacity();
    this.location = command.getLocation();
  }

}