package com.ps.parking.lot.services;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;

import com.ps.parking.lot.dao.FloorDao;
import com.ps.parking.lot.dao.ParkingLotDao;
import com.ps.parking.lot.dao.SlotDao;
import com.ps.parking.lot.dao.SlotOccupancyDao;
import com.ps.parking.lot.models.domain.enums.SlotSize;
import com.ps.parking.lot.models.entities.Floor;
import com.ps.parking.lot.models.entities.ParkingLot;
import com.ps.parking.lot.models.entities.Slot;
import com.ps.parking.lot.models.entities.SlotOccupancy;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = ParkingLotOnboardService.class)
public class ParkingLotOnboardServiceTest {

    @MockBean
    private ParkingLotDao parkingLotRepository;
    @MockBean
    private FloorDao floorRepository;
    @MockBean
    private SlotDao slotRepository;
    @MockBean
    private SlotOccupancyDao slotOccupancyRepository;
    

    @InjectMocks
    private  ParkingLotOnboardService service;

    private ParkingLotService parkingLotService;
    private ParkingLot parkingLot;
    private Floor floor;
    private Slot slot;
    private SlotOccupancy slotOccupancy;

    @BeforeEach
    public void setUp() {
        // parkingLotService = new ParkingLotService(parkingLotRepository,
        //         floorRepository, slotRepository, slotOccupancyRepository);
        parkingLot = ParkingLot.builder().id(1L).mnemonic("Test Parking Lot").build();
        floor = Floor.builder().id(1L).mnemonic(1).parkingLot(parkingLot).build();
        slot = Slot.builder().id(1L).mnemonic("A1").isAvailable(true).slotSize(SlotSize.LARGE)
                .parkingLot(parkingLot).floor(floor).build();
        slotOccupancy = SlotOccupancy.builder().id(1L).vehicleId(123).parkingLotId(1L).floorId(1L)
                .slotId(1L)
                .startTime(OffsetDateTime.now()).endTime(OffsetDateTime.now()).build();
    }


    @Test
    void test(){

    }

}

// @Test
// public void testOnBoardParkingLot_ValidInput_Success() {
// // setup
// OnboardParkingLotsRequestDto requestDto =
// OnboardParkingLotsRequestDto.builder()
// .parkingLotId("parkingLot1")
// .parkingFloorDetails(Arrays.asList(
// ParkingFloorDetails.builder()
// .floorMnemonic(1)
// .slots(Arrays.asList(
// SlotDetails.builder()
// .id("slot1")
// .slotSize(SlotSize.SMALL)
// .numberOfSlots(10)
// .build(),
// SlotDetails.builder()
// .id("slot2")
// .slotSize(SlotSize.MEDIUM)
// .numberOfSlots(20)
// .build()))
// .build(),
// ParkingFloorDetails.builder()
// .floorMnemonic(2)
// .slots(Arrays.asList(
// SlotDetails.builder()
// .id("slot3")
// .slotSize(SlotSize.LARGE)
// .numberOfSlots(30)
// .build(),
// SlotDetails.builder()
// .id("slot4")
// .slotSize(SlotSize.X_LARGE)
// .numberOfSlots(40)
// .build()))
// .build()))
// .build();
// ParkingLot parkingLot = ParkingLot.builder().id("parkingLot1").build();
// Floor floor1 =
// Floor.builder().id("floor1").mnemonic(1).parkingLot(parkingLot).build();
// Floor floor2 =
// Floor.builder().id("floor2").mnemonic(2).parkingLot(parkingLot).build();
// Slot slot1 =
// Slot.builder().id("slot1").slotSize(SlotSize.SMALL.getSize()).floor(floor1).build();
// Slot slot2 =
// Slot.builder().id("slot2").slotSize(SlotSize.MEDIUM.getSize()).floor(floor1).build();
// Slot slot3 =
// Slot.builder().id("slot3").slotSize(SlotSize.LARGE.getSize()).floor(floor2).build();
// Slot slot4 =
// Slot.builder().id("slot4").slotSize(SlotSize.X_LARGE.getSize()).floor(floor2).build();

// // mock
// when(parkingLotDao.save(any(ParkingLot.class))).thenReturn(parkingLot);
// when(floorDao.save(any(Floor.class))).thenReturn(floor1, floor2);
// }

// @Test
// public void
// testOnBoardParkingLot_WhenParkingLotDataIsValid_ShouldReturnSuccessfulResponse()
// {
// // Given
// Long parkingLotId = 1L;
// List<ParkingFloorDetails> parkingFloorDetails = new ArrayList<>();
// ParkingFloorDetails floor1 =
// ParkingFloorDetails.builder().floorMnemonic(1).build();
// ParkingFloorDetails floor2 =
// ParkingFloorDetails.builder().floorMnemonic(2).build();
// parkingFloorDetails.add(floor1);
// parkingFloorDetails.add(floor2);
// OnboardParkingLotsRequestDto request =
// OnboardParkingLotsRequestDto.builder().parkingLotId("1")
// .parkingFloorDetails(parkingFloorDetails).build();

// ParkingLot parkingLot = ParkingLot.builder().id(parkingLotId).build();
// Floor floor = Floor.builder().id(1L).build();
// Slot slot = Slot.builder().id(1L).build();

// Mockito.when(parkingLotDao.saveOnlyIfMnemonicNotExist(Mockito.any(ParkingLot.class))).thenReturn(parkingLot);
// Mockito.when(slotDao.save(Mockito.any(Slot.class))).thenReturn(slot);

// // When
// parkingLotOnboardService.onBoardParkingLot(request);

// // Then
// verify(parkingLotDao,
// times(1)).getParkingLotFromMnemonic(Mockito.any(String.class));
// verify(floorDao, times(2)).saveAll(Mockito.any(List.class));
// verify(slotDao, times(1)).save(Mockito.any(Slot.class));
// }

// @Test
// public void
// testOnBoardParkingLot_WhenParkingLotDataIsInvalid_ShouldThrowIllegalArgumentException()
// {
// // Given
// String parkingLotId = "";
// List<ParkingFloorDetails> parkingFloorDetails = new ArrayList<>();
// ParkingFloorDetails floor1 =
// ParkingFloorDetails.builder().floorMnemonic(1).build();
// ParkingFloorDetails floor2 =
// ParkingFloorDetails.builder().floorMnemonic(2).build();
// parkingFloorDetails.add(floor1);
// parkingFloorDetails.add(floor2);
// OnboardParkingLotsRequestDto request =
// OnboardParkingLotsRequestDto.builder().parkingLotId(parkingLotId)
// .parkingFloorDetails(parkingFloorDetails).build();

// // When
// try {
// parkingLotOnboardService.onBoardParkingLot(request);
// fail("Expected an IllegalArgumentException to be thrown");
// } catch (IllegalArgumentException ex) {
// // Then
// assertThat(ex.getMessage(), equalTo("Parking lot id can't be empty"));
// }
// }

// }
