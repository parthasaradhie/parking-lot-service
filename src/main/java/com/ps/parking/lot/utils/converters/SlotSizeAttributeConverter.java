package com.ps.parking.lot.utils.converters;

import java.util.Optional;

import com.ps.parking.lot.models.domain.enums.SlotSize;

import jakarta.persistence.AttributeConverter;

public class SlotSizeAttributeConverter implements AttributeConverter<SlotSize, Integer> {

    @Override
    public SlotSize convertToEntityAttribute(Integer dbData) {
        return SlotSize.getSlotSize(dbData).orElseThrow(() -> new IllegalArgumentException("Slot type doesn't exists"));
    }

    @Override
    public Integer convertToDatabaseColumn(SlotSize attribute) {
        return Optional.ofNullable(attribute).map(SlotSize::getSize)
                .orElseThrow(() -> new IllegalArgumentException("SlotSize cannot be null"));
    }
}
