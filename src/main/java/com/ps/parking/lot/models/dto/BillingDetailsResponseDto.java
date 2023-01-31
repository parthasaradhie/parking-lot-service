package com.ps.parking.lot.models.dto;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillingDetailsResponseDto {
	private OffsetDateTime parkingEnDateTime;
	private OffsetDateTime parkingStartDateTime;
}
