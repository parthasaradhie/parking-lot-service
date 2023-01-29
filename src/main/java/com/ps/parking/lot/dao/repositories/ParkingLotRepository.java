package com.ps.parking.lot.dao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ps.parking.lot.models.entities.ParkingLot;

@Component
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {

    public ParkingLot findByMnemonic(String mnemonic);

    default ParkingLot saveIfMnemonicNotExist(ParkingLot parkingLot) {
        return Optional.ofNullable(findByMnemonic(parkingLot.getMnemonic()))
                .orElseGet(() -> save(parkingLot));
    }

}
