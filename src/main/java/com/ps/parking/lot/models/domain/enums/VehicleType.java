package com.ps.parking.lot.models.domain.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum VehicleType {
    SMALL(1),
    LARGE(2),
    MEDIUM(3),
    X_LARGE(4);

    private int priority;
    private static Map<Integer, VehicleType> cacheMap = Arrays.stream(values())
            .collect(Collectors.toMap(VehicleType::getPriority, Function.identity()));

    private VehicleType(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return this.priority;
    }

    public static Optional<VehicleType> getVehicleType(int priority) {
        VehicleType vehicleType = cacheMap.get(priority);
        return Optional.ofNullable(vehicleType);
    }
}
