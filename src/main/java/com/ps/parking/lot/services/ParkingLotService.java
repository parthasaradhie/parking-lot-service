package com.ps.parking.lot.services;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ps.parking.lot.dao.ParkingLotDao;
import com.ps.parking.lot.dao.SlotDao;
import com.ps.parking.lot.dao.SlotOccupancyDao;
import com.ps.parking.lot.models.domain.enums.SlotSize;
import com.ps.parking.lot.models.dto.BillingDetailsResponseDto;
import com.ps.parking.lot.models.dto.SlotRequestDto;
import com.ps.parking.lot.models.dto.SlotResponseDto;
import com.ps.parking.lot.models.entities.ParkingLot;
import com.ps.parking.lot.models.entities.Slot;
import com.ps.parking.lot.models.entities.SlotOccupancy;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ParkingLotService {

	@Autowired
	private ParkingLotDao parkingLotDao;
	@Autowired
	private SlotDao slotDao;
	@Autowired
	private SlotOccupancyDao slotOccupancyDao;

	public Optional<BillingDetailsResponseDto> getParkingBill(SlotRequestDto slotRequestDto) {
		if (slotRequestDto == null) {
			return Optional.empty();
		}

		Optional<String> slotMnemonic = getSlotMnemonicFromSlotRequest(slotRequestDto);
		Optional<ParkingLot> parkingLotFromMnemonic = parkingLotDao
				.getParkingLotFromMnemonic(slotRequestDto.getParkingLotId());
		Optional<Slot> slotDetails = slotDao.findByMnemonicAndParkingLotId(slotMnemonic.orElse(null),
				parkingLotFromMnemonic.map(ParkingLot::getId).orElse(0L));
		Optional<SlotOccupancy> slotOccupancy = slotOccupancyDao.getSlotOccupancy(
				slotDetails.map(Slot::getId).orElse(0L), parkingLotFromMnemonic.map(ParkingLot::getId).orElse(0L));
		return slotOccupancy.map(slot -> BillingDetailsResponseDto.builder().parkingEnDateTime(slot.getEndTime())
				.parkingStartDateTime(slot.getStartTime()).build());

	}

	public SlotResponseDto getSlot(String parkingLotMnemonic, SlotSize slotSize) {
		Optional<ParkingLot> parkingLotFromMnemonic = parkingLotDao.getParkingLotFromMnemonic(parkingLotMnemonic);
		Optional<Slot> slotDetails = parkingLotFromMnemonic
				.flatMap(lot -> slotDao.lockAndGetValidSlot(slotSize, lot.getId()));
		SlotResponseDto slotResponseDto = slotDetails.map(this::getSlotResponseDto)
				.orElseGet(this::logAndReturnEmptyResponse);
		slotDetails.ifPresent(this::saveSlotOccupancy);

		return slotResponseDto;
	}

	private void saveSlotOccupancy(Slot slot) {
		slotOccupancyDao
				.save(SlotOccupancy.builder().startTime(OffsetDateTime.now()).floorId(slot.getFloor().getId())
						.parkingLotId(slot.getParkingLot().getId()).slotId(slot.getId()).build());
	}

	public void releaseSlots(SlotRequestDto slotRequestDto) {
		Optional<String> slotMnemonic = getSlotMnemonicFromSlotRequest(slotRequestDto);
		Optional<ParkingLot> parkingLotFromMnemonic = parkingLotDao
				.getParkingLotFromMnemonic(slotRequestDto.getParkingLotId());
		Optional<Slot> slotDetails = slotDao.findByMnemonicAndParkingLotId(slotMnemonic.orElse(null),
				parkingLotFromMnemonic.map(ParkingLot::getId).orElse(0L));
		slotDetails.filter(slot -> !slot.isAvailable()).ifPresent(slot -> releaseSlot(parkingLotFromMnemonic, slot));
	}

	private Optional<String> getSlotMnemonicFromSlotRequest(SlotRequestDto slotRequestDto) {
		String[] slotMnemonicAndFloorId = Optional.ofNullable(slotRequestDto.getSlotId())
				.map(id -> id.split("-")).orElse(new String[] { null, null });
		return Optional.ofNullable(slotMnemonicAndFloorId[1]);
	}

	private SlotResponseDto getSlotResponseDto(Slot slot) {
		return SlotResponseDto.builder().slot(slot.getFloor().getMnemonic() + "-" + slot.getMnemonic()).build();
	}

	private SlotResponseDto logAndReturnEmptyResponse() {
		log.info("[GET-SLOT] No Slot Found");
		return new SlotResponseDto();
	}

	private void releaseSlot(Optional<ParkingLot> parkingLotFromMnemonic, Slot slot) {
		slotDao.setSlotAsAvailable(slot, parkingLotFromMnemonic.map(ParkingLot::getId).orElse(0L));
		slotOccupancyDao.updateSlotOccupancyEndTime(slot.getId(),
				parkingLotFromMnemonic.map(ParkingLot::getId).orElse(0L), OffsetDateTime.now());
	}
}
