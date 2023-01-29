package com.ps.parking.lot.controllers.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ps.parking.lot.controllers.ParkingLotOnboardController;
import com.ps.parking.lot.models.dto.OnboardParkingLotsRequestDto;

@Component
public class ParkingLotOnboardControllerImpl implements ParkingLotOnboardController{

    @Override
    public ResponseEntity<OnboardParkingLotsRequestDto> onBoardParkingLot(OnboardParkingLotsRequestDto parkingLots) {
        throw new UnsupportedOperationException("Not Implemented yet");
    }
    
}
