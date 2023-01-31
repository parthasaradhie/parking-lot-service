package com.ps.parking.lot.models.dto;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BillingDetailsResponseDto {
	private OffsetDateTime parkingEnDateTime;
	private OffsetDateTime parkingStartDateTime;
}
