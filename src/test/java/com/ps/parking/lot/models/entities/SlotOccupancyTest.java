package com.ps.parking.lot.models.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

public class SlotOccupancyTest {
    private static final long ID = 123;
    private static final long PARKING_LOT_ID = 432;
    private static final long FLOOR_ID = 74;
    private static final Long SLOT_ID = 98L;
    private static final int VEHICLE_ID = 321;
    private static final SlotOccupancy.Builder SLOT_OCCUPANCY = SlotOccupancy.builder().id(ID).vehicleId(VEHICLE_ID)
            .parkingLotId(PARKING_LOT_ID).floorId(FLOOR_ID).slotId(SLOT_ID);

    @Test
    void testBuilder() {
        SlotOccupancy slotOccupancy = SLOT_OCCUPANCY.build();
        assertInstanceOf(SlotOccupancy.class, slotOccupancy);
    }

    void testGetFloorId() {
        SlotOccupancy slotOccupancy = SLOT_OCCUPANCY.build();
        assertEquals(FLOOR_ID, slotOccupancy.getFloorId());
    }

    @Test
    void testGetId() {
        SlotOccupancy slotOccupancy = SLOT_OCCUPANCY.build();
        assertEquals(ID, slotOccupancy.getId());
    }

    @Test
    void testGetParkingLotId() {
        SlotOccupancy slotOccupancy = SLOT_OCCUPANCY.build();
        assertEquals(PARKING_LOT_ID, slotOccupancy.getParkingLotId());
    }

    @Test
    void testGetSlotId() {
        SlotOccupancy slotOccupancy = SLOT_OCCUPANCY.build();
        assertEquals(SLOT_ID, slotOccupancy.getSlotId());

    }

    @Test
    void testGetVehicleId() {
        SlotOccupancy slotOccupancy = SLOT_OCCUPANCY.build();
        assertEquals(VEHICLE_ID, slotOccupancy.getVehicleId());
    }

}
