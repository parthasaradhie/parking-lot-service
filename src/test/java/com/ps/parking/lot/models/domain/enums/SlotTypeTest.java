package com.ps.parking.lot.models.domain.enums;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class SlotTypeTest {
    private static final int PRIORITY = 1;
    private static final String SMALL = "SMALL";
    private static final String LARGE = "LARGE";
    private static final String MEDIUM = "MEDIUM";
    private static final String X_LARGE = "X_LARGE";
    private static final SlotType SLOT_TYPE = SlotType.SMALL;

    @Test
    void testGetPriority() {

        assertEquals(1, PRIORITY);
    }

    @Test
    void testGetSlotType() {
        assertEquals(SlotType.SMALL, SlotType.getSlotType(PRIORITY).get());
    }

    @Test
    void testValueOf() {
        assertDoesNotThrow(() -> SlotType.valueOf(SMALL));
        assertDoesNotThrow(() -> SlotType.valueOf(LARGE));
        assertDoesNotThrow(() -> SlotType.valueOf(MEDIUM));
        assertDoesNotThrow(() -> SlotType.valueOf(X_LARGE));
    }

    @Test
    void testValues() {

    }
}
