package com.ps.parking.lot.models.domain.enums;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SlotSizeTest {

    @Test
    void testGetSize() {
        assertEquals(1, SlotSize.SMALL.getSize());
        assertEquals(2, SlotSize.MEDIUM.getSize());
        assertEquals(3, SlotSize.LARGE.getSize());
        assertEquals(4, SlotSize.X_LARGE.getSize());
    }

    @Test
    void testGetSlotSize() {
        Optional<SlotSize> optionalSmall = SlotSize.getSlotSize(1);
        assertTrue(optionalSmall.isPresent());
        assertEquals(SlotSize.SMALL, optionalSmall.get());

        Optional<SlotSize> optionalMedium = SlotSize.getSlotSize(2);
        assertTrue(optionalMedium.isPresent());
        assertEquals(SlotSize.MEDIUM, optionalMedium.get());

        Optional<SlotSize> optionalLarge = SlotSize.getSlotSize(3);
        assertTrue(optionalLarge.isPresent());
        assertEquals(SlotSize.LARGE, optionalLarge.get());

        Optional<SlotSize> optionalXLarge = SlotSize.getSlotSize(4);
        assertTrue(optionalXLarge.isPresent());
        assertEquals(SlotSize.X_LARGE, optionalXLarge.get());

        Optional<SlotSize> optionalInvalid = SlotSize.getSlotSize(5);
        assertFalse(optionalInvalid.isPresent());
    }
}