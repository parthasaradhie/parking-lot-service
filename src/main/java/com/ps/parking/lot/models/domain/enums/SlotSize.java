package com.ps.parking.lot.models.domain.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum SlotSize {
	LARGE(3), MEDIUM(2), SMALL(1), X_LARGE(4);

	private static Map<Integer, SlotSize> cacheMap = Arrays.stream(values())
			.collect(Collectors.toMap(SlotSize::getSize, Function.identity()));
	public static Optional<SlotSize> getSlotSize(int priority) throws IllegalArgumentException {
		SlotSize slotSize = cacheMap.get(priority);
		return Optional.ofNullable(slotSize);
	}

	private int size;

	private SlotSize(int priority) {
		this.size = priority;
	}

	public int getSize() {
		return this.size;
	}
}
