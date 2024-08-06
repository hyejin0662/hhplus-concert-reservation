package com.concert_reservation.api.interfaces.controller.concert;

import com.concert_reservation.api.interfaces.controller.concert.dto.ConcertOptionDto;
import com.concert_reservation.api.application.concert.ConcertOptionFacade;
import com.concert_reservation.common.model.WebResponseData;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/concert-options")
@RequiredArgsConstructor
public class ConcertOptionController {

    private final ConcertOptionFacade concertOptionFacade;

    @PostMapping
    @Operation(summary = "콘서트 옵션 생성", description = "새로운 콘서트 옵션을 생성합니다.")
    public WebResponseData<ConcertOptionDto.Response> createConcertOption(@Valid @RequestBody ConcertOptionDto.Request concertOptionDto) {
        ConcertOptionDto.Response response = concertOptionFacade.createConcertOption(concertOptionDto);
        return WebResponseData.ok(response);
    }

    @GetMapping("/{concertOptionId}")
    @Operation(summary = "콘서트 옵션 조회", description = "콘서트 옵션 ID로 콘서트 옵션 정보를 조회합니다.")
    public WebResponseData<ConcertOptionDto.Response> getConcertOption(@PathVariable Long concertOptionId) {
        ConcertOptionDto.Response response = concertOptionFacade.getConcertOption(concertOptionId);
        return WebResponseData.ok(response);
    }

    @PutMapping("/{concertOptionId}")
    @Operation(summary = "콘서트 옵션 업데이트", description = "콘서트 옵션 ID로 콘서트 옵션 정보를 업데이트합니다.")
    public WebResponseData<ConcertOptionDto.Response> updateConcertOption(@PathVariable Long concertOptionId, @Valid @RequestBody ConcertOptionDto.Request concertOptionDto) {
        ConcertOptionDto.Response response = concertOptionFacade.updateConcertOption(concertOptionId, concertOptionDto);
        return WebResponseData.ok(response);
    }

    @DeleteMapping("/{concertOptionId}")
    @Operation(summary = "콘서트 옵션 삭제", description = "콘서트 옵션 ID로 콘서트 옵션을 삭제합니다.")
    public ResponseEntity<Void> deleteConcertOption(@PathVariable Long concertOptionId) {
        concertOptionFacade.deleteConcertOption(concertOptionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "모든 콘서트 옵션 조회")
    public WebResponseData<List<ConcertOptionDto.Response>> getConcertOptions() {
        List<ConcertOptionDto.Response> responses = concertOptionFacade.getConcertOptions();
        return WebResponseData.ok(responses);
    }
}
