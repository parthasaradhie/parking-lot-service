package com.ps.parking.lot.models.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;


import com.ps.parking.lot.models.domain.enums.SlotType;


public class SlotDetailsTest {
    private static final String ID= "123";
    private static final SlotType SLOT_TYPE=SlotType.SMALL;
    private static final int NUMBER_OF_SLOTS =3;
    private static final SlotDetails.Builder SLOT_DETAILS= SlotDetails.builder().id(ID).slotType(SLOT_TYPE).numberOfSlots(NUMBER_OF_SLOTS);

    @Test
    void testBuilder() {
        SlotDetails slotDetails=SLOT_DETAILS.build();
        assertInstanceOf(SlotDetails.class, slotDetails);

    }

    @Test
    void testGetId() {
        SlotDetails slotDetails=SLOT_DETAILS.build();
        assertEquals(ID, slotDetails.getId());

    }

    @Test
    void testGetNumberOfSlots() {
        SlotDetails slotDetails=SLOT_DETAILS.build();
        assertEquals(NUMBER_OF_SLOTS, slotDetails.getNumberOfSlots());

    }

    @Test
    void testGetSlotType() {
        SlotDetails slotDetails=SLOT_DETAILS.build();
        assertEquals(SLOT_TYPE, slotDetails.getSlotType());

    }
}
