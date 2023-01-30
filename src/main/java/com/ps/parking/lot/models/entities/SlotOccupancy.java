package com.ps.parking.lot.models.entities;

import java.time.OffsetDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "slot_occupancy")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SlotOccupancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int vehicleId;
    private Long parkingLotId;
    private Long floorId;
    private Long slotId;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
}