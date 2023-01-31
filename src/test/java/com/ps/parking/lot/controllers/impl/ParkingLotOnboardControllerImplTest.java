package com.ps.parking.lot.controllers.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

import com.ps.parking.lot.models.dto.OnboardParkingLotsRequestDto;
import com.ps.parking.lot.services.ParkingLotOnboardService;

@ExtendWith(MockitoExtension.class)
public class ParkingLotOnboardControllerImplTest {

    @Mock
    private ParkingLotOnboardService parkingLotOnboardService;

    @InjectMocks
    private ParkingLotOnboardControllerImpl parkingLotOnboardController;

    private OnboardParkingLotsRequestDto onboardParkingLotsRequestDto;

    @BeforeEach
    public void setUp() {
        onboardParkingLotsRequestDto = new OnboardParkingLotsRequestDto();
    }

    @Test
    public void testOnBoardParkingLot() {
        parkingLotOnboardController.onBoardParkingLot(onboardParkingLotsRequestDto);

        verify(parkingLotOnboardService).onBoardParkingLot(onboardParkingLotsRequestDto);
    }

    @Test
    void testOnBoardParkingLot_response() {
      doNothing().when(parkingLotOnboardService).onBoardParkingLot(any(OnboardParkingLotsRequestDto.class));
      var response = parkingLotOnboardController.onBoardParkingLot(new OnboardParkingLotsRequestDto());
      assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }
}

