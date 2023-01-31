package com.ps.parking.lot.models.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ps.parking.lot.models.domain.enums.SlotSize;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class SlotDetailsTest {
	private static final String ID = "123";
	private static final int NUMBER_OF_SLOTS = 3;
	private static final SlotSize SLOT_TYPE = SlotSize.SMALL;
	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	private static final SlotDetails.Builder SLOT_DETAILS = SlotDetails.builder().id(ID).slotSize(SLOT_TYPE)
			.numberOfSlots(NUMBER_OF_SLOTS);

	@Test
	void testBuilder() {
		SlotDetails slotDetails = SLOT_DETAILS.build();
		assertInstanceOf(SlotDetails.class, slotDetails);

	}

	@Test
	void testGetId() {
		SlotDetails slotDetails = SLOT_DETAILS.build();
		assertEquals(ID, slotDetails.getId());

	}

	@Test
	void testGetNumberOfSlots() {
		SlotDetails slotDetails = SLOT_DETAILS.build();
		assertEquals(NUMBER_OF_SLOTS, slotDetails.getNumberOfSlots());

	}

	@Test
	void testGetSlotSize() {
		SlotDetails slotDetails = SLOT_DETAILS.build();
		assertEquals(SLOT_TYPE, slotDetails.getSlotSize());
	}

	@Test
	void testGettersAndSetters() {
		SlotDetails slotDetails = SlotDetails.builder().id("id").slotSize(SlotSize.SMALL).numberOfSlots(1).build();

		assertEquals("id", slotDetails.getId());
		assertEquals(SlotSize.SMALL, slotDetails.getSlotSize());
		assertEquals(1, slotDetails.getNumberOfSlots());
	}

	@Test
	public void testSetIdForSlotDetails() {
		SlotDetails slotDetails = SlotDetails.builder().id("123").build();
		assertEquals("123", slotDetails.getId());
	}

	@Test
	public void testSetNumberOfSlotsForSlotDetails() {
		SlotDetails slotDetails = SlotDetails.builder().numberOfSlots(5).build();
		assertEquals(5, slotDetails.getNumberOfSlots());
	}

	@Test
	public void testSetSlotSizeForSlotDetails() {
		SlotDetails slotDetails = SlotDetails.builder().slotSize(SlotSize.LARGE).build();
		assertEquals(SlotSize.LARGE, slotDetails.getSlotSize());
	}

	@Test
	void testValidationNotNullSlotSize() {
		SlotDetails slotDetails = SlotDetails.builder().id("id").numberOfSlots(1).build();
		Set<ConstraintViolation<SlotDetails>> constraintViolations = validator.validate(slotDetails);
		assertEquals(1, constraintViolations.size());
		assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
	}
}
