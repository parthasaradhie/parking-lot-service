package com.ps.parking.lot.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ps.parking.lot.dao.repositories.FloorRepository;
import com.ps.parking.lot.models.entities.Floor;
import com.ps.parking.lot.models.entities.ParkingLot;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = FloorDao.class)
public class FloorDaoTest {
    private static final Integer FLOOR_MNEMONIC = 5;
    private static final long PARKING_LOT_DB_COLUMN_ID = 2L;

    @MockBean
    private FloorRepository floorRepositoryMock;

    @InjectMocks
    private FloorDao floorDao;

    @Mock
    private Floor floorMock;

    @Test
    void testFindByMnemonic() {
        when(floorRepositoryMock.findByMnemonic(FLOOR_MNEMONIC)).thenReturn(Optional.of(floorMock));
        Optional<Floor> actualOutput = floorDao.findByMnemonic(FLOOR_MNEMONIC);
        assertEquals(Optional.of(floorMock), actualOutput);
    }

    @Test
    void testGetAllFloorsByParkingLotId() {
        List<Floor> floors = List.of(floorMock);
        when(floorRepositoryMock.findByParkingLotId(ParkingLot.builder().id(PARKING_LOT_DB_COLUMN_ID).build()))
                .thenReturn(floors);
        List<Floor> actualOutput = floorDao.getAllFloorsByParkingLotId(PARKING_LOT_DB_COLUMN_ID);
        assertEquals(floors, actualOutput);
    }

    @Test
    void testSaveAll() {
        List<Floor> floors = List.of(floorMock);
        floorDao.saveAll(floors);
        verify(floorRepositoryMock).saveAll(floors);
    }
}
