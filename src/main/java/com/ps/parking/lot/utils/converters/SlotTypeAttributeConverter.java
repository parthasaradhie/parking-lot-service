package com.ps.parking.lot.utils.converters;

import java.util.Optional;

import com.ps.parking.lot.models.domain.enums.SlotType;

import jakarta.persistence.AttributeConverter;

public class SlotTypeAttributeConverter implements AttributeConverter<SlotType, Integer> {

    @Override
    public SlotType convertToEntityAttribute(Integer dbData) {
        return SlotType.getSlotType(dbData).orElseThrow(() -> new IllegalArgumentException("Slot type doesn't exists"));
    }

    @Override
    public Integer convertToDatabaseColumn(SlotType attribute) {
        return Optional.ofNullable(attribute).map(SlotType::getPriority)
                .orElseThrow(() -> new IllegalArgumentException("SlotType cannot be null"));
    }
}
