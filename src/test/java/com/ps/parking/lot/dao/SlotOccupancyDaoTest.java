package com.ps.parking.lot.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ps.parking.lot.dao.repositories.SlotOccupancyRepository;
import com.ps.parking.lot.models.entities.SlotOccupancy;

@ExtendWith(MockitoExtension.class)
public class SlotOccupancyDaoTest {
    private long slotId = 1L;
    private long parkingLotId = 3L;
    private OffsetDateTime startTime = OffsetDateTime.now();
    private OffsetDateTime endTime = startTime.plusHours(1);
    SlotOccupancy expectedSlotOccupancy = SlotOccupancy.builder()
            .endTime(endTime)
            .startTime(startTime)
            .floorId(null)
            .parkingLotId(parkingLotId)
            .slotId(slotId)
            .build();

    @Mock
    private SlotOccupancyRepository slotOccupancyRepository;

    @InjectMocks
    private SlotOccupancyDao slotOccupancyDao;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    public void testGetSlotOccupancy() {
        when(slotOccupancyRepository.findFirstBySlotIdAndParkingLotIdOrderByIdDesc(slotId, parkingLotId))
                .thenReturn(Optional.of(expectedSlotOccupancy));
        Optional<SlotOccupancy> actualSlotOccupancy = slotOccupancyDao.getSlotOccupancy(slotId, parkingLotId);
        assertTrue(actualSlotOccupancy.isPresent());
        assertEquals(expectedSlotOccupancy, actualSlotOccupancy.get());
    }

    @Test
    public void testGetSlotOccupancyEndTimeNull() {
        when(slotOccupancyRepository.findBySlotIdAndParkingLotIdAndEndTimeNull(slotId, parkingLotId))
                .thenReturn(Optional.of(expectedSlotOccupancy));

        Optional<SlotOccupancy> actualSlotOccupancy = slotOccupancyDao.getSlotOccupancyEndTimeNull(slotId,
                parkingLotId);
        assertTrue(actualSlotOccupancy.isPresent());
        assertEquals(expectedSlotOccupancy, actualSlotOccupancy.get());
    }

    @Test
    public void testSave() {
        when(slotOccupancyRepository.save(expectedSlotOccupancy)).thenReturn(expectedSlotOccupancy);

        SlotOccupancy actualSlotOccupancy = slotOccupancyDao.save(expectedSlotOccupancy);
        assertEquals(expectedSlotOccupancy, actualSlotOccupancy);
    }

    @Test
    public void testUpdateSlotOccupancyEndTime() {
        when(slotOccupancyRepository.findBySlotIdAndParkingLotIdAndEndTimeNull(slotId, parkingLotId))
                .thenReturn(Optional.of(expectedSlotOccupancy));
            slotOccupancyDao.updateSlotOccupancyEndTime(slotId,parkingLotId, OffsetDateTime.now());
        verify(slotOccupancyRepository).save(any());
    }
}