package com.ps.parking.lot.models.domain.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum SlotType {
    SMALL(1),
    LARGE(2),
    MEDIUM(3),
    X_LARGE(4);

    private int priority;
    private static Map<Integer, SlotType> cacheMap = Arrays.stream(values())
            .collect(Collectors.toMap(SlotType::getPriority, Function.identity()));

    private SlotType(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return this.priority;
    }

    public static Optional<SlotType> getSlotType(int priority) throws IllegalArgumentException {
        SlotType slotType = cacheMap.get(priority);
        return Optional.ofNullable(slotType);
    }
}
