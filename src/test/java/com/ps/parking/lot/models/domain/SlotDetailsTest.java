package com.ps.parking.lot.models.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ps.parking.lot.models.domain.enums.SlotType;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;


public class SlotDetailsTest {
    private static final String ID= "123";
    private static final SlotType SLOT_TYPE=SlotType.SMALL;
    private static final int NUMBER_OF_SLOTS =3;
    private static final SlotDetails.Builder SLOT_DETAILS= SlotDetails.builder().id(ID).slotType(SLOT_TYPE).numberOfSlots(NUMBER_OF_SLOTS);
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

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

    @Test
    void testGettersAndSetters() {
        SlotDetails slotDetails = SlotDetails.builder().id("id").slotType(SlotType.SMALL).numberOfSlots(1).build();

        assertEquals("id", slotDetails.getId());
        assertEquals(SlotType.SMALL, slotDetails.getSlotType());
        assertEquals(1, slotDetails.getNumberOfSlots());
    }

    @Test
    public void testSetIdForSlotDetails() {
        SlotDetails slotDetails = SlotDetails.builder().id("123").build();
        assertEquals("123", slotDetails.getId());
    }

    @Test
    public void testSetSlotSizeForSlotDetails() {
        SlotDetails slotDetails = SlotDetails.builder().slotType(SlotType.LARGE).build();
        assertEquals(SlotType.LARGE, slotDetails.getSlotType());
    }

    @Test
    public void testSetNumberOfSlotsForSlotDetails() {
        SlotDetails slotDetails = SlotDetails.builder().numberOfSlots(5).build();
        assertEquals(5, slotDetails.getNumberOfSlots());
    }

    @Test
    void testValidationNotNullSlotSize() {
        SlotDetails slotDetails = SlotDetails.builder().id("id").numberOfSlots(1).build();
        Set<ConstraintViolation<SlotDetails>> constraintViolations = validator.validate(slotDetails);
        assertEquals(1, constraintViolations.size());
        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
    }
}
