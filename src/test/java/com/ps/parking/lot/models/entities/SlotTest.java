package com.ps.parking.lot.models.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import com.ps.parking.lot.models.domain.enums.SlotSize;

public class SlotTest {

	private static final Floor FLOOR = new Floor();
	private static final long ID = 12;
	private static final boolean IS_AVAILABLE = false;
	private static final String MNEMONIC = "123PS";
	private static final ParkingLot PARKING_LOT = new ParkingLot();
	private static final SlotSize SLOT_TYPE = SlotSize.SMALL;
	private static final Slot.Builder SLOT = Slot.builder().floor(FLOOR).id(ID).mnemonic(MNEMONIC).slotSize(SLOT_TYPE)
			.parkingLot(PARKING_LOT).isAvailable(IS_AVAILABLE);

	@Test
	void testBuilder() {
		Slot slot = SLOT.build();
		new Slot();
		assertInstanceOf(Slot.class, slot);
	}

	@Test
	void testGetFloor() {
		Slot slot = SLOT.build();
		assertInstanceOf(Floor.class, slot.getFloor());

	}

	@Test
	void testGetId() {
		Slot slot = SLOT.build();
		assertEquals(ID, slot.getId());
	}

	@Test
	void testGetMnemonic() {
		Slot slot = SLOT.build();
		assertEquals(MNEMONIC, slot.getMnemonic());
	}

	@Test
	void testGetParkingLot() {
		Slot slot = SLOT.build();
		assertInstanceOf(ParkingLot.class, slot.getParkingLot());
	}

	@Test
	void testGetSlotSize() {
		Slot slot = SLOT.build();
		assertEquals(SLOT_TYPE, slot.getSlotSize());
	}

	@Test
	void testIsAvailable() {
		Slot slot = SLOT.build();
		assertEquals(IS_AVAILABLE, slot.isAvailable());
	}
}
