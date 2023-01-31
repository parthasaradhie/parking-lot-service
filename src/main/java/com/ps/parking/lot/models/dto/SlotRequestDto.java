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
public class SlotRequestDto {
	@NotBlank
	private String parkingLotId;
	@NotBlank
	private String slotId;
}
