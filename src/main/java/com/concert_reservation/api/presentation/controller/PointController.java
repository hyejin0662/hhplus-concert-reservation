package com.concert_reservation.api.presentation.controller;

import com.concert_reservation.api.application.dto.request.PointRequest;
import com.concert_reservation.api.application.dto.response.PointResponse;
import com.concert_reservation.api.application.facade.PointFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/points")
@RequiredArgsConstructor
public class PointController {

    private final PointFacade pointFacade;


    @PostMapping
    public ResponseEntity<PointResponse> createPoint(@Valid @RequestBody PointRequest pointRequest) {
        PointResponse response = pointFacade.createPoint(pointRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{pointId}")
    public ResponseEntity<PointResponse> getPoint(@PathVariable Long pointId) {
        PointResponse response = pointFacade.getPoint(pointId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{pointId}")
    public ResponseEntity<PointResponse> updatePoint(@PathVariable Long pointId, @Valid @RequestBody PointRequest pointRequest) {
        PointResponse response = pointFacade.updatePoint(pointId, pointRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{pointId}")
    public ResponseEntity<Void> deletePoint(@PathVariable Long pointId) {
        pointFacade.deletePoint(pointId);
        return ResponseEntity.noContent().build();
    }
}
