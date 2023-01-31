package com.ps.parking.lot.controllers.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ps.parking.lot.controllers.ParkingLotController;
import com.ps.parking.lot.models.domain.enums.SlotSize;
import com.ps.parking.lot.models.dto.BillingDetailsResponseDto;
import com.ps.parking.lot.models.dto.SlotRequestDto;
import com.ps.parking.lot.models.dto.SlotResponseDto;
import com.ps.parking.lot.services.ParkingLotService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ParkingLotControllerImpl implements ParkingLotController {

	@Autowired
	private ParkingLotService parkingLotService;

	@Override
	public ResponseEntity<Object> getParkingBill(SlotRequestDto slotRequestDto) {
		log.info("[GET-PARKING-BILL] request received for generating bill {}", slotRequestDto);
		Optional<BillingDetailsResponseDto> parkingBill = parkingLotService.getParkingBill(slotRequestDto);
		log.info("[GET-PARKING-BILL] bill generated {}", parkingBill);
		if (parkingBill.isPresent()) {
			return ResponseEntity.ok(parkingBill.get());
		}
		return ResponseEntity.noContent().build();
	}
	
	@Override
	public ResponseEntity<SlotResponseDto> getSlot(String parkingLotMnemonic, SlotSize slotSize) {
		log.info("[GET-SLOT] request received for booking a lot {} {}", parkingLotMnemonic, slotSize);
		SlotResponseDto response = parkingLotService.getSlot(parkingLotMnemonic, slotSize);
		log.info("[GET-SLOT] slot allocated for booking  {} {} {}", parkingLotMnemonic, slotSize, response);
		
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<Void> releaseSlots(SlotRequestDto slotRequestDto) {
		log.info("[GET-SLOT] request received for releasing the slot {}", slotRequestDto);
		parkingLotService.releaseSlots(slotRequestDto);
		log.info("[GET-SLOT] successfully released the slot {}", slotRequestDto);

		return ResponseEntity.ok().build();
	}
}
