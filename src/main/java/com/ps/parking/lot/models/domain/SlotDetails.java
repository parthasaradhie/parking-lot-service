package com.ps.parking.lot.models.domain;

import com.ps.parking.lot.models.domain.enums.SlotType;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(builderClassName = "Builder")
@NoArgsConstructor
@AllArgsConstructor
public class SlotDetails {
    private String id;
    @NotNull
    private SlotType slotType;
    private int numberOfSlots;
}
