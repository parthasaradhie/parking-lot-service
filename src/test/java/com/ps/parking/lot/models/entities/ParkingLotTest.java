package com.ps.parking.lot.models.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ParkingLotTest {
	private static final long ID = 123444;
	private static final String MNEMONIC = "12";
	private static final ParkingLot.Builder PARKING__LOT_BUILDER = ParkingLot.builder().id(ID).mnemonic(MNEMONIC);

	@Test
	void testBuilder() {
		ParkingLot parkingLot = PARKING__LOT_BUILDER.build();
		assertInstanceOf(ParkingLot.class, parkingLot);
	}

	@Test
	void testGetId() {
		ParkingLot parkingLot = PARKING__LOT_BUILDER.build();
		assertEquals(ID, parkingLot.getId());
	}

	@Test
	void testGetMnemonic() {
		ParkingLot parkingLot = PARKING__LOT_BUILDER.build();
		assertEquals(MNEMONIC, parkingLot.getMnemonic());
	}

	@Test
	void testEquals() {
		ParkingLot parkingLot1 = PARKING__LOT_BUILDER.build();
		ParkingLot parkingLot2 = PARKING__LOT_BUILDER.build();
		assertTrue(parkingLot1.equals(parkingLot2));
		assertTrue(parkingLot1.hashCode() == parkingLot2.hashCode());
	}

}
