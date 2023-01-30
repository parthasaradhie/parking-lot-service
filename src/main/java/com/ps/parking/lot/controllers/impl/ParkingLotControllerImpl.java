package com.ps.parking.lot.controllers.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ps.parking.lot.controllers.ParkingLotController;
import com.ps.parking.lot.models.domain.enums.SlotSize;
import com.ps.parking.lot.models.dto.BillingDetailsResponseDto;
import com.ps.parking.lot.models.dto.ReleaseSlotRequestDto;
import com.ps.parking.lot.models.dto.SlotResponseDto;
import com.ps.parking.lot.services.ParkingLotService;

@Controller

public class ParkingLotControllerImpl implements ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @Override
    public ResponseEntity<SlotResponseDto> getSlot(String parkingLotMnemonic, SlotSize slotSize) {
        SlotResponseDto response = parkingLotService.getSlot(parkingLotMnemonic, slotSize);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> releaseSlots(ReleaseSlotRequestDto releaseSlotRequestDto) {
        parkingLotService.releaseSlots(releaseSlotRequestDto);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> getParkingBill(
            ReleaseSlotRequestDto releaseSlotRequestDto) {
                Optional<BillingDetailsResponseDto> parkingBill = parkingLotService.getParkingBill(releaseSlotRequestDto);
                if(parkingBill.isPresent()){
                    return ResponseEntity.ok(parkingBill.get());
                }
        return ResponseEntity.noContent().build();
    }
}
