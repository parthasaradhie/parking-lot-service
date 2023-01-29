package com.ps.parking.lot.services;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ps.parking.lot.dao.FloorDao;
import com.ps.parking.lot.dao.ParkingLotDao;
import com.ps.parking.lot.dao.SlotDao;
import com.ps.parking.lot.models.domain.ParkingFloorDetails;
import com.ps.parking.lot.models.dto.OnboardParkingLotsRequestDto;
import com.ps.parking.lot.models.entities.Floor;
import com.ps.parking.lot.models.entities.ParkingLot;

@Component
public class ParkingLotOnboardService {

    @Autowired
    private FloorDao floorDao;
    @Autowired
    private ParkingLotDao parkingLotDao;
    @Autowired
    private SlotDao slotDao;

    public void onBoardParkingLot(OnboardParkingLotsRequestDto parkingLots) {
        String parkingLotId = parkingLots.getParkingLotId();
        ParkingLot parkingLotDetails = parkingLotDao
                .saveOnlyIfMnemonicNotExist(ParkingLot.builder().mnemonic(parkingLotId).build());
        List<Floor> existingFloors = floorDao.getAllFloorsByParkingLotId(parkingLotDetails.getId());
        Set<Integer> existingFloorsMnemonics = existingFloors.stream()
                .map(Floor::getMnemonic).collect(Collectors.toSet());
        List<Floor> newFloors = parkingLots.getParkingFloorDetails().stream()
                .map(ParkingFloorDetails::getFloorMnemonic).filter(Objects::nonNull)
                .filter(floorMnemonic -> !existingFloorsMnemonics.contains(floorMnemonic))
                .map(floorMnemonic -> getFloor(parkingLotDetails, floorMnemonic))
                .collect(Collectors.toList());
        floorDao.saveAll(newFloors);
        Map<Integer, Floor> combinedFloors = Stream.concat(existingFloors.stream(), newFloors.stream())
                .collect(Collectors.toMap(Floor::getMnemonic, Function.identity()));

        // parkingLots.getParkingFloorDetails().stream().map(floorDetail -> {
        //     floorDetail.getSlots().stream().map(slot -> {
        //         Floor currentFloor = IntStream.range(1, slot.getNumberOfSlots() + 1).mapToObj(number -> {
        //             combinedFloors.get(floorDetail.getFloorMnemonic());
        //             return Slot.builder()
        //                     .isAvailable(true)
        //                     .mnemonic(number)
        //                     .parkingLot(ParkingLot.builder().mnemonic(parkingLotId).build())
        //                     .slotType(slot.getSlotType())
        //                     .floor(Floor.builder().id(currentFloor.getId()).build())
        //                     .build();
        //         });
        //         return null;
        //     });
        //     return null;
        // });

    }

    private Floor getFloor(ParkingLot parkingLotDetails, Integer floorMnemonic) {
        return Floor.builder().mnemonic(floorMnemonic).parkingLotId(parkingLotDetails.getId()).build();
    }
}
