package com.ps.parking.lot.controllers.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.ps.parking.lot.models.domain.enums.SlotSize;
import com.ps.parking.lot.models.dto.BillingDetailsResponseDto;
import com.ps.parking.lot.models.dto.ReleaseSlotRequestDto;
import com.ps.parking.lot.models.dto.SlotResponseDto;
import com.ps.parking.lot.services.ParkingLotService;

@ExtendWith(MockitoExtension.class)
public class ParkingLotControllerImplTest {

    @Mock
    private ParkingLotService parkingLotService;

    @InjectMocks
    private ParkingLotControllerImpl controller;

    @Test
    public void testGetParkingBill() {
        ReleaseSlotRequestDto request = new ReleaseSlotRequestDto();
        BillingDetailsResponseDto bill = new BillingDetailsResponseDto();
        when(parkingLotService.getParkingBill(request)).thenReturn(Optional.of(bill));

        ResponseEntity<Object> result = controller.getParkingBill(request);

        assertEquals(ResponseEntity.ok(bill), result);
    }

    @Test
    public void testGetParkingBill_empty() {
        ReleaseSlotRequestDto request = new ReleaseSlotRequestDto();
        when(parkingLotService.getParkingBill(request)).thenReturn(Optional.empty());

        ResponseEntity<Object> result = controller.getParkingBill(request);

        assertEquals(ResponseEntity.noContent().build(), result);
    }

    @Test
    public void testGetSlot() {
        String parkingLotMnemonic = "PL1";
        SlotSize slotSize = SlotSize.LARGE;
        SlotResponseDto response = new SlotResponseDto();
        when(parkingLotService.getSlot(parkingLotMnemonic, slotSize)).thenReturn(response);

        ResponseEntity<SlotResponseDto> result = controller.getSlot(parkingLotMnemonic, slotSize);

        assertEquals(ResponseEntity.ok(response), result);
    }

    @Test
    public void testReleaseSlots() {
        ReleaseSlotRequestDto request = new ReleaseSlotRequestDto();

        controller.releaseSlots(request);

        verify(parkingLotService).releaseSlots(request);
    }
}
