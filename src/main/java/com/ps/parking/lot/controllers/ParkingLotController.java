package com.ps.parking.lot.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.parking.lot.models.domain.enums.SlotSize;
import com.ps.parking.lot.models.dto.SlotRequestDto;
import com.ps.parking.lot.models.dto.SlotResponseDto;

@RestController
@RequestMapping("/")
public interface ParkingLotController {

	@PostMapping(path = "/parkingBill", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getParkingBill(@RequestBody SlotRequestDto slotRequestDto);

	@GetMapping(path = "/getSlot/{parkingLotId}/{slotSize}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SlotResponseDto> getSlot(@PathVariable("parkingLotId") String parkingLotMnemonic,
			@PathVariable("slotSize") SlotSize slotSize);

	@PutMapping(path = "/releaseSlots", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> releaseSlots(@RequestBody SlotRequestDto slotRequestDto);

}
