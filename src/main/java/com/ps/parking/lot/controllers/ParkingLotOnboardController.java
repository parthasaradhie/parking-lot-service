package com.ps.parking.lot.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.parking.lot.models.dto.OnboardParkingLotsRequestDto;

@RestController
@RequestMapping("admin")
public interface ParkingLotOnboardController {

    @PostMapping(path = "/onboard-parking-lot", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> onBoardParkingLot(
            @RequestBody OnboardParkingLotsRequestDto parkingLots);
}
