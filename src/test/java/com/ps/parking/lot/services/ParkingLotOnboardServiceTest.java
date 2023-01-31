package com.ps.parking.lot.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ps.parking.lot.dao.FloorDao;
import com.ps.parking.lot.dao.ParkingLotDao;
import com.ps.parking.lot.dao.SlotDao;
import com.ps.parking.lot.dao.SlotOccupancyDao;
import com.ps.parking.lot.models.dto.OnboardParkingLotsRequestDto;
import com.ps.parking.lot.models.entities.Floor;
import com.ps.parking.lot.models.entities.ParkingLot;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = ParkingLotOnboardService.class)
public class ParkingLotOnboardServiceTest {

	private Floor floor;
	@MockBean
	private FloorDao floorRepository;
	
	@MockBean
	private ParkingLotDao parkingLotDao;

	@InjectMocks
	private ParkingLotOnboardService parkingLotOnboardService;

	@MockBean
	private SlotOccupancyDao slotOccupancyRepository;
	@MockBean
	private SlotDao slotRepository;

	@Test
	public void testOnBoardParkingLot() {
		OnboardParkingLotsRequestDto parkingLots = mock(OnboardParkingLotsRequestDto.class);
		ParkingLotDao parkingLotDao = mock(ParkingLotDao.class);
		FloorDao floorDao = mock(FloorDao.class);
		SlotDao slotDao = mock(SlotDao.class);

		ParkingLot parkingLot = mock(ParkingLot.class);
		when(parkingLotDao.saveOnlyIfMnemonicNotExist(any(ParkingLot.class))).thenReturn(parkingLot);

		List<Floor> existingFloors = mock(List.class);
		when(floorDao.getAllFloorsByParkingLotId(anyLong())).thenReturn(existingFloors);

		doNothing().when(floorDao).saveAll(anyList());

		parkingLotOnboardService.onBoardParkingLot(parkingLots);

		verify(parkingLotDao).saveOnlyIfMnemonicNotExist(any(ParkingLot.class));
		verify(floorDao).getAllFloorsByParkingLotId(anyLong());
		verify(floorDao).saveAll(anyList());

	}

}