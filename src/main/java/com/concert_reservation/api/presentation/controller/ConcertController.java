package com.concert_reservation.api.presentation.controller;

import com.concert_reservation.api.application.dto.request.ConcertRequest;
import com.concert_reservation.api.application.dto.response.ConcertResponse;
import com.concert_reservation.api.application.facade.ConcertFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/concerts")
@RequiredArgsConstructor
public class ConcertController {

	private final ConcertFacade concertFacade;

	@PostMapping
	public ResponseEntity<ConcertResponse> createConcert(@Valid @RequestBody ConcertRequest concertRequest) {
		ConcertResponse response = concertFacade.createConcert(concertRequest);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{concertId}")
	public ResponseEntity<ConcertResponse> getConcert(@PathVariable Long concertId) {
		ConcertResponse response = concertFacade.getConcert(concertId);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{concertId}")
	public ResponseEntity<ConcertResponse> updateConcert(@PathVariable Long concertId, @Valid @RequestBody ConcertRequest concertRequest) {
		ConcertResponse response = concertFacade.updateConcert(concertId, concertRequest);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{concertId}")
	public ResponseEntity<Void> deleteConcert(@PathVariable Long concertId) {
		concertFacade.deleteConcert(concertId);
		return ResponseEntity.noContent().build();
	}
}
