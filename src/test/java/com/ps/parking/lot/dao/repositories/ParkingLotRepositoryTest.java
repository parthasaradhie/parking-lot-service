package com.ps.parking.lot.dao.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ps.parking.lot.models.entities.ParkingLot;

@ExtendWith(MockitoExtension.class)
public class ParkingLotRepositoryTest {
    private static final String MNEMONIC = "TEST";
    private static final String MNEMONIC_2 = "TEST2";
    ParkingLot parkingLot = ParkingLot.builder().mnemonic(MNEMONIC).build();

    private ParkingLotRepository parkingLotRepository = spy(ParkingLotRepository.class);

    @Test
    public void whenSaveIfMnemonicExistCalled() {
       
        when(parkingLotRepository.findByMnemonic(MNEMONIC)).thenReturn(Optional.of(parkingLot));

        ParkingLot actualParkingLot = parkingLotRepository.saveIfMnemonicNotExist(parkingLot);
        assertEquals(parkingLot, actualParkingLot);
    }
    @Test
    public void whenSaveIfMnemonicNotExistCalled_thenSaveParkingLot() {
       
        when(parkingLotRepository.findByMnemonic(MNEMONIC_2)).thenReturn(Optional.of(parkingLot));
        when(parkingLotRepository.save(any())).thenReturn(parkingLot);

        ParkingLot actualParkingLot = parkingLotRepository.saveIfMnemonicNotExist(parkingLot);
        assertEquals(parkingLot, actualParkingLot);
    }
}
