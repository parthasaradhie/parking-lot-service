package com.ps.parking.lot.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

import com.ps.parking.lot.dao.FloorDao;
import com.ps.parking.lot.dao.ParkingLotDao;
import com.ps.parking.lot.dao.SlotDao;
import com.ps.parking.lot.dao.SlotOccupancyDao;
import com.ps.parking.lot.models.domain.ParkingFloorDetails;
import com.ps.parking.lot.models.domain.SlotDetails;
import com.ps.parking.lot.models.domain.enums.SlotSize;
import com.ps.parking.lot.models.dto.OnboardParkingLotsRequestDto;
import com.ps.parking.lot.models.entities.Floor;
import com.ps.parking.lot.models.entities.ParkingLot;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = ParkingLotOnboardService.class)
class ParkingLotOnboardServiceTest {

	private static final int NEW_NUMBER_OF_SLOTS_NEEDED = 2;
	private static final SlotSize LARGE_SLOT_SIZE = SlotSize.LARGE;
	private static final String PARKING_LOT_ID = "PARKING_LOT_ID1";
	private static final Integer FLOOR_MNEMONIC = 5;
	private static final Integer FLOOR_MNEMONIC_2 = 2;

	@MockBean
	private FloorDao floorDaoMock;

	@MockBean
	private ParkingLotDao parkingLotDao;

	@InjectMocks
	private ParkingLotOnboardService parkingLotOnboardService;

	@MockBean
	private SlotOccupancyDao slotOccupancyRepository;
	@MockBean
	private SlotDao slotRepository;

	@Mock
	private ParkingLot parkingLotMock;

	SlotDetails sloDetails = null;
	ParkingFloorDetails parkingFloorDetail = null;
	List<ParkingFloorDetails> parkingFloorDetails = new ArrayList<>();
	OnboardParkingLotsRequestDto onBoardParkingLots = null;

	@BeforeEach
	void beforeEachTest() {
		sloDetails = SlotDetails.builder().numberOfSlots(NEW_NUMBER_OF_SLOTS_NEEDED)
				.slotSize(LARGE_SLOT_SIZE).build();
		parkingFloorDetail = ParkingFloorDetails.builder()
				.floorMnemonic(FLOOR_MNEMONIC)
				.slots(List.of(sloDetails))
				.build();
		parkingFloorDetails.add(parkingFloorDetail);
		onBoardParkingLots = OnboardParkingLotsRequestDto.builder()
				.parkingFloorDetails(parkingFloorDetails).parkingLotId(PARKING_LOT_ID).build();
	}

	@Test
	void testOnBoardParkingLot() {
		ParkingLot parkingLot = mock(ParkingLot.class);
		when(parkingLotDao.saveOnlyIfMnemonicNotExist(any(ParkingLot.class))).thenReturn(parkingLot);
		List<Floor> existingFloors = new ArrayList<>();
		Floor floorMock = mock(Floor.class);
		when(floorMock.getMnemonic()).thenReturn(FLOOR_MNEMONIC_2);
		existingFloors.add(floorMock);
		when(floorDaoMock.getAllFloorsByParkingLotId(anyLong())).thenReturn(existingFloors);
		doNothing().when(floorDaoMock).saveAll(anyList());
		
		parkingLotOnboardService.onBoardParkingLot(onBoardParkingLots);
		
		verify(parkingLotDao).saveOnlyIfMnemonicNotExist(any(ParkingLot.class));
		verify(floorDaoMock).getAllFloorsByParkingLotId(anyLong());
		verify(floorDaoMock).saveAll(anyList());

	}

}