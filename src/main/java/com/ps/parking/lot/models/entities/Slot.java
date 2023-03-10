package com.ps.parking.lot.models.entities;

import com.ps.parking.lot.models.domain.enums.SlotSize;
import com.ps.parking.lot.utils.converters.SlotSizeAttributeConverter;

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

	@ManyToOne(optional = false)
	@JoinColumn(name = "floorId", nullable = false, updatable = false)
	private Floor floor;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private boolean isAvailable;

	private String mnemonic;

	@ManyToOne(optional = false)
	@JoinColumn(name = "parkingLotId", nullable = false, updatable = false)
	private ParkingLot parkingLot;

	@Convert(converter = SlotSizeAttributeConverter.class)
	private SlotSize slotSize;
}
