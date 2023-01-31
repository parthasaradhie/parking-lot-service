package com.ps.parking.lot.models.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ps.parking.lot.models.domain.ParkingFloorDetails;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class OnboardParkingLotsRequestDto {
	private List<ParkingFloorDetails> parkingFloorDetails;
	@NotBlank
	private String parkingLotId;
}
