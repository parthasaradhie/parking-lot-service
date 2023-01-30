package com.ps.parking.lot.models.entities;

import com.ps.parking.lot.models.domain.enums.SlotType;
import com.ps.parking.lot.utils.converters.SlotTypeAttributeConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int mnemonic;
    private boolean isAvailable;

    @Convert(converter = SlotTypeAttributeConverter.class)
    private SlotType slotType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "parkingLotId", nullable = false, updatable = false)
    private ParkingLot parkingLot;

    @ManyToOne(optional = false)
    @JoinColumn(name = "floorId", nullable = false, updatable = false)
    private Floor floor;
}
