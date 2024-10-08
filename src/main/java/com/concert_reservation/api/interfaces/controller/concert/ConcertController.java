package com.concert_reservation.api.interfaces.controller.concert;

import com.concert_reservation.api.interfaces.controller.concert.dto.request.ConcertRequest;
import com.concert_reservation.api.interfaces.controller.concert.dto.response.ConcertResponse;
import com.concert_reservation.api.application.concert.ConcertFacade;
import com.concert_reservation.common.model.WebResponseData;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/concerts")
@RequiredArgsConstructor
public class ConcertController {

	private final ConcertFacade concertFacade;

	@PostMapping
	@Operation(summary = "콘서트 생성", description = "새로운 콘서트를 생성합니다.")
	public WebResponseData<ConcertResponse> createConcert(@Valid @RequestBody ConcertRequest concertRequest) {
		ConcertResponse response = concertFacade.createConcert(concertRequest);
		return WebResponseData.ok(response);
	}

	@GetMapping("/{concertId}")
	@Operation(summary = "콘서트 조회", description = "콘서트 ID로 콘서트 정보를 조회합니다.")
	public WebResponseData<ConcertResponse> getConcert(@PathVariable Long concertId) {
		ConcertResponse response = concertFacade.getConcert(concertId);
		return WebResponseData.ok(response);
	}

	@PutMapping("/{concertId}")
	@Operation(summary = "콘서트 업데이트", description = "콘서트 ID로 콘서트 정보를 업데이트합니다.")
	public WebResponseData<ConcertResponse> updateConcert(@PathVariable Long concertId, @Valid @RequestBody ConcertRequest concertRequest) {
		ConcertResponse response = concertFacade.updateConcert(concertId, concertRequest);
		return WebResponseData.ok(response);
	}

	@DeleteMapping("/{concertId}")
	@Operation(summary = "콘서트 삭제", description = "콘서트 ID로 콘서트를 삭제합니다.")
	public ResponseEntity<Void> deleteConcert(@PathVariable Long concertId) {
		concertFacade.deleteConcert(concertId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	@Operation(summary = "모든 콘서트 조회")
	public WebResponseData<List<ConcertResponse>> getConcerts() {
		List<ConcertResponse> responses = concertFacade.getConcerts();
		return WebResponseData.ok(responses);
	}
}
