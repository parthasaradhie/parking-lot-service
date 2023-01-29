package com.ps.parking.lot.models.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ps.parking.lot.models.domain.ParkingFloorDetails;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OnboardParkingLotsRequestDto {
    @NotBlank
    private String parkingLotId;
    private List<ParkingFloorDetails> parkingFloorDetails;
}
