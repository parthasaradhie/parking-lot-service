package com.ps.parking.lot.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ps.parking.lot.dao.repositories.ParkingLotRepository;
import com.ps.parking.lot.models.entities.ParkingLot;

@Component
public class ParkingLotDao {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public ParkingLot saveOnlyIfMnemonicNotExist(ParkingLot parkingLot) {
        return parkingLotRepository.saveIfMnemonicNotExist(parkingLot);
    }

    public Optional<ParkingLot> getParkingLotFromMnemonic(String mnemonic) {
        return parkingLotRepository.findByMnemonic(mnemonic);
    }
}
