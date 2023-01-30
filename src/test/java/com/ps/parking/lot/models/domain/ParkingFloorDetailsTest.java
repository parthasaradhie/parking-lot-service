package com.ps.parking.lot.models.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.List;

import org.junit.jupiter.api.Test;


public class ParkingFloorDetailsTest {

    private static final Integer FLOOR_MNEMONIC = 1234;
    private static final SlotDetails SLOT_DETAILS_1=new SlotDetails();
    private static final SlotDetails SLOT_DETAILS_2=new SlotDetails();
    private static final List<SlotDetails> SLOTS = List.of(SLOT_DETAILS_1,SLOT_DETAILS_2);
    
    private static final ParkingFloorDetails.Builder PARKING_FLOOR_DETAILS= ParkingFloorDetails.builder().slots(SLOTS).floorMnemonic(FLOOR_MNEMONIC);
    @Test
    void testBuilder() {
        ParkingFloorDetails parkingFloorDetails= PARKING_FLOOR_DETAILS.build();
        assertInstanceOf(ParkingFloorDetails.class, parkingFloorDetails);
    }
    @Test
    void testGetFloorMnemonic() {
        ParkingFloorDetails parkingFloorDetails= PARKING_FLOOR_DETAILS.build();
        assertEquals(FLOOR_MNEMONIC, parkingFloorDetails.getFloorMnemonic());
    }
    @Test
    void testGetSlots() {
        ParkingFloorDetails parkingFloorDetails= PARKING_FLOOR_DETAILS.build();
        assertEquals(SLOTS, parkingFloorDetails.getSlots());
    }
}
