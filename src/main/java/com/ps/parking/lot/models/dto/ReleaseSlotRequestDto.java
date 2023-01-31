package com.ps.parking.lot.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReleaseSlotRequestDto {
	@NotBlank
	private String parkingLotId;
	@NotBlank
	private String slotId;
}
