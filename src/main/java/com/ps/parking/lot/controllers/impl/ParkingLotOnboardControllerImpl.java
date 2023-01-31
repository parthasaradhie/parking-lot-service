package com.ps.parking.lot.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ps.parking.lot.controllers.ParkingLotOnboardController;
import com.ps.parking.lot.models.dto.OnboardParkingLotsRequestDto;
import com.ps.parking.lot.services.ParkingLotOnboardService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ParkingLotOnboardControllerImpl implements ParkingLotOnboardController {

	@Autowired
	private ParkingLotOnboardService parkingLotOnboardService;

	@Override
	public ResponseEntity<Void> onBoardParkingLot(OnboardParkingLotsRequestDto parkingLots) {
		log.info("[GET-SLOT] request received for onboarding parking lot {}", parkingLots);
		parkingLotOnboardService.onBoardParkingLot(parkingLots);
		return ResponseEntity.ok().build();
	}
}
