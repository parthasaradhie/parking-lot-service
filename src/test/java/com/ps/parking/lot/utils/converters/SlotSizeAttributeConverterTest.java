package com.ps.parking.lot.utils.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.ps.parking.lot.models.domain.enums.SlotSize;

class SlotSizeAttributeConverterTest {
	private static final Integer IN_VALID_SLOT_SIZE = 20;
	private static final Integer VALID_SLOT_SIZE = 1;
	private static final SlotSize VALID_SLOT_SIZE_ENUM = SlotSize.getSlotSize(VALID_SLOT_SIZE).get();

	@Test
	void convertToDatabaseColumn() {
		SlotSizeAttributeConverter converter = new SlotSizeAttributeConverter();
		Integer slotSizeExpected = converter.convertToDatabaseColumn(VALID_SLOT_SIZE_ENUM);
		assertEquals(slotSizeExpected, VALID_SLOT_SIZE);
	}

	@Test
	void convertToDatabaseColumn_InvalidSlot_IllegalArgumentException_onNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			SlotSizeAttributeConverter converter = new SlotSizeAttributeConverter();
			converter.convertToDatabaseColumn(null);
		});
	}

	@Test
	void test_convertToEntityAttribute() {
		SlotSizeAttributeConverter converter = new SlotSizeAttributeConverter();
		SlotSize slotSizeExpected = converter.convertToEntityAttribute(VALID_SLOT_SIZE);
		assertEquals(slotSizeExpected, VALID_SLOT_SIZE_ENUM);
	}

	@Test
	void test_convertToEntityAttribute_InvalidSlot_IllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> {
			SlotSizeAttributeConverter converter = new SlotSizeAttributeConverter();
			converter.convertToEntityAttribute(IN_VALID_SLOT_SIZE);
		});
	}
}
