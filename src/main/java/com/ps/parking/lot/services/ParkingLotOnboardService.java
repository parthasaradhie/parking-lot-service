package com.ps.parking.lot.services;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ps.parking.lot.dao.FloorDao;
import com.ps.parking.lot.dao.ParkingLotDao;
import com.ps.parking.lot.dao.SlotDao;
import com.ps.parking.lot.models.domain.ParkingFloorDetails;
import com.ps.parking.lot.models.domain.SlotDetails;
import com.ps.parking.lot.models.dto.OnboardParkingLotsRequestDto;
import com.ps.parking.lot.models.entities.Floor;
import com.ps.parking.lot.models.entities.ParkingLot;
import com.ps.parking.lot.models.entities.Slot;

@Component
public class ParkingLotOnboardService {

	@Autowired
	private FloorDao floorDao;
	@Autowired
	private ParkingLotDao parkingLotDao;
	@Autowired
	private SlotDao slotDao;

	private Slot geSlot(long parkingLotId, Map<Integer, Floor> combinedFloors, ParkingFloorDetails floorDetail,
			SlotDetails slot, int number) {
		Floor currentFloor = combinedFloors.get(floorDetail.getFloorMnemonic());
		return Slot.builder().isAvailable(true).mnemonic(slot.getSlotSize().getSize() + "" + number)
				.parkingLot(ParkingLot.builder().id(parkingLotId).build()).slotSize(slot.getSlotSize())
				.floor(Floor.builder().id(currentFloor.getId()).build()).build();
	}

	private Floor getFloor(ParkingLot parkingLotDetails, Integer floorMnemonic) {
		return Floor.builder().mnemonic(floorMnemonic)
				.parkingLot(ParkingLot.builder().id(parkingLotDetails.getId()).build()).build();
	}

	private Stream<Slot> getSlotsStreamFromSlotDetails(long parkingLotId, Map<Integer, Floor> combinedFloors,
			ParkingFloorDetails floorDetail, SlotDetails slot) {
		return IntStream.range(1, slot.getNumberOfSlots() + 1)
				.mapToObj(number -> geSlot(parkingLotId, combinedFloors, floorDetail, slot, number));
	}

	private Stream<Slot> getSlotStreamFromFloorDetail(long parkingLotId, Map<Integer, Floor> combinedFloors,
			ParkingFloorDetails floorDetail) {
		return floorDetail.getSlots().stream()
				.map(slot -> getSlotsStreamFromSlotDetails(parkingLotId, combinedFloors, floorDetail, slot))
				.flatMap(Function.identity());
	}

	public void onBoardParkingLot(OnboardParkingLotsRequestDto parkingLots) {
		String parkingLotId = parkingLots.getParkingLotId();
		ParkingLot parkingLotDetails = parkingLotDao
				.saveOnlyIfMnemonicNotExist(ParkingLot.builder().mnemonic(parkingLotId).build());
		List<Floor> existingFloors = floorDao.getAllFloorsByParkingLotId(parkingLotDetails.getId());
		Set<Integer> existingFloorsMnemonics = existingFloors.stream().map(Floor::getMnemonic)
				.collect(Collectors.toSet());
		List<Floor> newFloors = parkingLots.getParkingFloorDetails().stream().map(ParkingFloorDetails::getFloorMnemonic)
				.filter(Objects::nonNull).filter(floorMnemonic -> !existingFloorsMnemonics.contains(floorMnemonic))
				.map(floorMnemonic -> getFloor(parkingLotDetails, floorMnemonic)).collect(Collectors.toList());
		floorDao.saveAll(newFloors);
		Map<Integer, Floor> combinedFloors = Stream.concat(existingFloors.stream(), newFloors.stream())
				.collect(Collectors.toMap(Floor::getMnemonic, Function.identity()));

		List<Slot> slots = parkingLots.getParkingFloorDetails().stream().map(
				floorDetail -> getSlotStreamFromFloorDetail(parkingLotDetails.getId(), combinedFloors, floorDetail))
				.flatMap(Function.identity()).toList();
		slotDao.saveAll(slots);
	}
}
