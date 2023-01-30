package com.ps.parking.lot.models.entities;

import com.ps.parking.lot.models.domain.enums.VehicleType;
import com.ps.parking.lot.utils.converters.VehicleTypeAttributeConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "slot_occupancy")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class SlotOccupancy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int vehicleId;
    private int parkingLotId;
    private int floorId;
    private int slotId;
    private boolean isAvailable;
    @Convert(converter = VehicleTypeAttributeConverter.class)
    private VehicleType vehicleType;
}
