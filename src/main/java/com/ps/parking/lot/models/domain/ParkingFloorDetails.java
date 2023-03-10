package com.ps.parking.lot.models.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(builderClassName = "Builder")
@NoArgsConstructor
@AllArgsConstructor
public class ParkingFloorDetails {
	@NotBlank
	@JsonProperty("floorNumber")
	private Integer floorMnemonic;
	private List<SlotDetails> slots;
}
