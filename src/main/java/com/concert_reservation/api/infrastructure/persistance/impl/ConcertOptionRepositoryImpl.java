package com.concert_reservation.api.infrastructure.persistance.impl;

import com.concert_reservation.api.business.model.entity.ConcertOption;
import com.concert_reservation.api.business.model.entity.Seat;
import com.concert_reservation.api.business.repo.ConcertOptionRepository;
import com.concert_reservation.api.infrastructure.persistance.orm.ConcertOptionJpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ConcertOptionRepositoryImpl implements ConcertOptionRepository {


    private final ConcertOptionJpaRepository concertOptionJpaRepository;

    @Override
    public Optional<ConcertOption> findById(Long concertOptionId) {
        return concertOptionJpaRepository.findById(concertOptionId);
    }

    @Override
    public List<ConcertOption> findAll() {
        return concertOptionJpaRepository.findAll();
    }

    @Override
    public ConcertOption save(ConcertOption concertOption) {
        return concertOptionJpaRepository.save(concertOption);
    }

    @Override
    public void deleteById(Long concertOptionId) {
        concertOptionJpaRepository.deleteById(concertOptionId);
    }

    @Override
    public List<ConcertOption> findAllByConcertId(Long concertId) {
        return concertOptionJpaRepository.findAllByConcertId(concertId);
    }

}