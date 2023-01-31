package com.ps.parking.lot.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ps.parking.lot.dao.repositories.ParkingLotRepository;
import com.ps.parking.lot.models.entities.ParkingLot;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = ParkingLotDao.class)
class ParkingLotDaoTest {
    private static final String PARKING_LOT_ID = "PARKING_LOT_ID1";

    @Autowired
    private ParkingLotDao parkingLotDao;

    @MockBean
    private ParkingLotRepository parkingLotRepository;

    @Mock
    ParkingLot parkingLot;

    @Test
    void testGetParkingLotFromMnemonic() {
       
        Optional<ParkingLot> expected = Optional.of(parkingLot);

        when(parkingLotRepository.findByMnemonic(PARKING_LOT_ID)).thenReturn(expected);

        Optional<ParkingLot> result = parkingLotDao.getParkingLotFromMnemonic(PARKING_LOT_ID);
        assertTrue(result.isPresent());
        assertEquals(expected, result);
    }

    @Test
    void testSaveOnlyIfMnemonicNotExist() {
        when(parkingLotRepository.saveIfMnemonicNotExist(parkingLot)).thenReturn(parkingLot);

        ParkingLot result = parkingLotDao.saveOnlyIfMnemonicNotExist(parkingLot);
        assertEquals(parkingLot, result);
    }
}