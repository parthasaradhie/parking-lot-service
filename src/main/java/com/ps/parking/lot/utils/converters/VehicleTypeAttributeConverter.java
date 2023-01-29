package com.ps.parking.lot.utils.converters;

import java.util.Optional;

import com.ps.parking.lot.models.domain.enums.VehicleType;

import jakarta.persistence.AttributeConverter;

public class VehicleTypeAttributeConverter implements AttributeConverter<VehicleType, Integer> {

    @Override
    public VehicleType convertToEntityAttribute(Integer dbData) {
        return VehicleType.getVehicleType(dbData)
                .orElseThrow(() -> new IllegalArgumentException("Slot type doesn't exists"));
    }

    @Override
    public Integer convertToDatabaseColumn(VehicleType attribute) {
        return Optional.ofNullable(attribute).map(VehicleType::getPriority)
                .orElseThrow(() -> new IllegalArgumentException("VehicleType cannot be null"));
    }
}
