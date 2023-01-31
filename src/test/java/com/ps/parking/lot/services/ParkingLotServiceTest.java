package com.ps.parking.lot.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.util.Pair;

import com.ps.parking.lot.dao.FloorDao;
import com.ps.parking.lot.dao.ParkingLotDao;
import com.ps.parking.lot.dao.SlotDao;
import com.ps.parking.lot.dao.SlotOccupancyDao;
import com.ps.parking.lot.models.domain.ParkingFloorDetails;
import com.ps.parking.lot.models.domain.SlotDetails;
import com.ps.parking.lot.models.domain.enums.SlotSize;
import com.ps.parking.lot.models.dto.BillingDetailsResponseDto;
import com.ps.parking.lot.models.dto.SlotRequestDto;
import com.ps.parking.lot.models.dto.SlotResponseDto;
import com.ps.parking.lot.models.entities.Floor;
import com.ps.parking.lot.models.entities.ParkingLot;
import com.ps.parking.lot.models.entities.Slot;
import com.ps.parking.lot.models.entities.SlotOccupancy;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = ParkingLotService.class)
class ParkingLotServiceTest {

    private static final int NEW_NUMBER_OF_SLOTS_NEEDED = 2;
    private static final SlotSize LARGE_SLOT_SIZE = SlotSize.LARGE;
    private static final String PARKING_LOT_ID = "PARKING_LOT_ID1";
    private static final String SLOT_ID = "33-11";
    private static final String SLOT_MNEMONIC = "11";
    private static final Integer FLOOR_MNEMONIC = 5;
    private static final Long SLOT_DB_ID = 21L;
    private static final Long PARKING_LOT_DB_COLUMN_ID = 2L;
    private static final ParkingLot.Builder PARKING_LOT_BUILDER = ParkingLot.builder().id(PARKING_LOT_DB_COLUMN_ID)
            .mnemonic(PARKING_LOT_ID);

    @MockBean
    private FloorDao floorDaoMock;

    @MockBean
    private ParkingLotDao parkingLotDao;

    @InjectMocks
    private ParkingLotService parkingLotService;

    @MockBean
    private SlotOccupancyDao slotOccupancyDao;
    @MockBean
    private SlotDao slotDao;

    private SlotDetails sloDetails = null;
    private ParkingFloorDetails parkingFloorDetail = null;
    private List<ParkingFloorDetails> parkingFloorDetails = new ArrayList<>();
    private SlotRequestDto slotRequestDto;

    @BeforeEach
    void beforeEachTest() {
        sloDetails = SlotDetails.builder().numberOfSlots(NEW_NUMBER_OF_SLOTS_NEEDED)
                .slotSize(LARGE_SLOT_SIZE).build();
        parkingFloorDetail = ParkingFloorDetails.builder()
                .floorMnemonic(FLOOR_MNEMONIC)
                .slots(List.of(sloDetails))
                .build();
        parkingFloorDetails.add(parkingFloorDetail);
        slotRequestDto = SlotRequestDto.builder().parkingLotId(PARKING_LOT_ID).slotId(SLOT_ID)
                .build();
    }

    @Test
    void testGetParkingBillWhenInputIsNull() {
        Optional<BillingDetailsResponseDto> parkingBill = parkingLotService.getParkingBill(null);
        assertTrue(parkingBill.isEmpty());
    }

    @Test
    void testGetParkingBill() {
        setUpForSlotReleaseWithSlotAvailability(true);
        OffsetDateTime currentTime = OffsetDateTime.now();
        SlotOccupancy slotOccupancy = SlotOccupancy.builder().startTime(currentTime)
                .endTime(currentTime).build();
        when(slotOccupancyDao.getSlotOccupancy(SLOT_DB_ID, PARKING_LOT_DB_COLUMN_ID))
                .thenReturn(Optional.of(slotOccupancy));
        Optional<BillingDetailsResponseDto> parkingBill = parkingLotService.getParkingBill(slotRequestDto);

        assertTrue(parkingBill.isPresent());
        assertEquals(currentTime, parkingBill.get().getParkingEnDateTime());
        assertEquals(currentTime, parkingBill.get().getParkingStartDateTime());
    }

    @Test
    void testGetSlot() {
        Pair<Slot, Optional<ParkingLot>> data = setUpForSlotReleaseWithSlotAvailability(false);

        when(slotDao.lockAndGetValidSlot(LARGE_SLOT_SIZE, data.getSecond().get().getId()))
                .thenReturn(Optional.of(data.getFirst()));
        when(slotOccupancyDao.save(any())).thenReturn(null);
        SlotResponseDto slotResponse = parkingLotService.getSlot(PARKING_LOT_ID, LARGE_SLOT_SIZE);
        verify(slotOccupancyDao).save(any());
        assertEquals(data.getFirst().getFloor().getMnemonic() + "-" + data.getFirst().getMnemonic(),
                slotResponse.getSlot());

    }

    @Test
    void testReleaseSlots() {
        Pair<Slot, Optional<ParkingLot>> data = setUpForSlotReleaseWithSlotAvailability(false);
        parkingLotService.releaseSlots(slotRequestDto);

        verify(slotDao).setSlotAsAvailable(data.getFirst(), data.getSecond().get().getId());
        ArgumentCaptor<Long> slotIdCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Long> parkingLotIdCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<OffsetDateTime> offSetDateTimeCaptor = ArgumentCaptor.forClass(OffsetDateTime.class);
        verify(slotOccupancyDao).updateSlotOccupancyEndTime(slotIdCaptor.capture(),
                parkingLotIdCaptor.capture(),
                offSetDateTimeCaptor.capture());
        assertEquals(SLOT_DB_ID, slotIdCaptor.getValue());
        assertEquals(PARKING_LOT_DB_COLUMN_ID, parkingLotIdCaptor.getValue());
        assertTrue(OffsetDateTime.now().toEpochSecond()
                - offSetDateTimeCaptor.getValue().toEpochSecond() < 1000);
    }

    @Test
    void test_noSlotsAvailableToRelease() {
        parkingLotService.releaseSlots(slotRequestDto);

        verify(slotDao, times(0)).setSlotAsAvailable(any(), anyLong());
        verify(slotOccupancyDao, times(0)).updateSlotOccupancyEndTime(anyLong(), anyLong(), any());
    }

    private Pair<Slot, Optional<ParkingLot>> setUpForSlotReleaseWithSlotAvailability(boolean isSLotAvailable) {
        Optional<ParkingLot> parkingLot = Optional.of(PARKING_LOT_BUILDER.build());
        when(parkingLotDao.getParkingLotFromMnemonic(PARKING_LOT_ID)).thenReturn(parkingLot);

        Slot slot = Slot.builder().id(SLOT_DB_ID).floor(Floor.builder().mnemonic(FLOOR_MNEMONIC).build())
                .parkingLot(parkingLot.get())
                .isAvailable(isSLotAvailable).build();

        when(slotDao.findByMnemonicAndParkingLotId(SLOT_MNEMONIC, PARKING_LOT_DB_COLUMN_ID))
                .thenReturn(Optional.of(slot));
        doNothing().when(slotDao).setSlotAsAvailable(slot, parkingLot.get().getId());
        doNothing().when(slotOccupancyDao).updateSlotOccupancyEndTime(anyLong(), anyLong(), any());

        return Pair.of(slot, parkingLot);
    }
}
