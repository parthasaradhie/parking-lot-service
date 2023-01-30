package com.ps.parking.lot.models.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import com.ps.parking.lot.models.domain.enums.SlotType;

public class SlotTest {

    private static final long ID=12;
    private static final int MNEMONIC= 123;
    private static final boolean IS_AVAILABLE= false;    
    private static final SlotType SLOT_TYPE =SlotType.SMALL;
    private static final ParkingLot PARKING_LOT= new ParkingLot();
    private static final Floor FLOOR =new Floor();
    private static final Slot.Builder  SLOT= Slot.builder().floor(FLOOR).id(ID).mnemonic(MNEMONIC).slotType(SLOT_TYPE).parkingLot(PARKING_LOT).isAvailable(IS_AVAILABLE);
    @Test
    void testBuilder() {
        Slot slot= SLOT.build();
        new Slot();
        assertInstanceOf(Slot.class, slot);
    }

    @Test
    void testGetFloor() {
        Slot slot= SLOT.build();
        assertInstanceOf(Floor.class, slot.getFloor());
        
    }

    @Test
    void testGetId() {
        Slot slot= SLOT.build();
        assertEquals(ID, slot.getId());
    }

    @Test
    void testGetMnemonic() {
        Slot slot= SLOT.build();
        assertEquals(MNEMONIC, slot.getMnemonic());
    }

    @Test
    void testGetParkingLot() {
        Slot slot= SLOT.build();
        assertInstanceOf(ParkingLot.class, slot.getParkingLot());
    }

    @Test
    void testGetSlotType() {
        Slot slot= SLOT.build();
        assertEquals(SLOT_TYPE, slot.getSlotType());
    }

    @Test
    void testIsAvailable() {
        Slot slot= SLOT.build();
        assertEquals(IS_AVAILABLE, slot.isAvailable());
    }
}
