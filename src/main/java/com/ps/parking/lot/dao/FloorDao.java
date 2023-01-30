package com.ps.parking.lot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ps.parking.lot.dao.repositories.FloorRepository;
import com.ps.parking.lot.models.entities.Floor;
import com.ps.parking.lot.models.entities.ParkingLot;

@Component
public class FloorDao{

    @Autowired
    private FloorRepository floorRepository;

    public void saveAll(List<Floor> floors) {
        floorRepository.saveAll(floors);
    }

    public List<Floor> getAllFloorsByParkingLotId(long parkingLotId) {
        return floorRepository.findByParkingLotId(ParkingLot.builder().id(parkingLotId).build());
    }

    public Optional<Floor> findByMnemonic(int mnemonic){
        return floorRepository.findByMnemonic(mnemonic);
    }
}
