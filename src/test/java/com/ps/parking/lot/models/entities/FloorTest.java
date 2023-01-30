package com.ps.parking.lot.models.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

public class FloorTest {

    private static final long ID = 123444;
    private static final int MNEMONIC = 123;
    private static final long PARKING_LOT_ID = 123444;

    private static final ParkingLot PARKING_LOT = new ParkingLot();
    private static final Floor.Builder FLOOR = Floor.builder().id(ID).mnemonic(MNEMONIC)
            .parkingLot(ParkingLot.builder().id(PARKING_LOT_ID).build()).parkingLot(PARKING_LOT);

    @Test
    void testBuilder() {
        Floor floor = FLOOR.build();
        assertInstanceOf(Floor.class, floor);

    }

    @Test
    void testGetId() {
        Floor floor = FLOOR.build();
        assertEquals(ID, floor.getId());
    }

    @Test
    void testGetMnemonic() {
        Floor floor = FLOOR.build();
        assertEquals(MNEMONIC, floor.getMnemonic());
    }

    @Test
    void testGetParkingLot() {
        Floor floor = FLOOR.build();
        assertInstanceOf(ParkingLot.class, floor.getParkingLot());
    }

    @Test
    void testGetParkingLotId() {
        Floor floor = FLOOR.build();
        assertEquals(PARKING_LOT_ID, floor.getParkingLot().getId());

    }
}
