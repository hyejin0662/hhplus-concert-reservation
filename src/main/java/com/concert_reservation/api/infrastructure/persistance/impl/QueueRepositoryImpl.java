package com.concert_reservation.api.infrastructure.persistance.impl;

import com.concert_reservation.api.business.repo.QueueRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.QueueJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QueueRepositoryImpl implements QueueRepository {

  private final QueueJpaRepository queueJpaRepository;

}
