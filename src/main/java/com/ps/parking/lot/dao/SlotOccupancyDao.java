package com.ps.parking.lot.dao;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ps.parking.lot.dao.repositories.SlotOccupancyRepository;
import com.ps.parking.lot.models.entities.SlotOccupancy;

@Component
public class SlotOccupancyDao {
	@Autowired
	private SlotOccupancyRepository slotOccupancyRepository;

	public Optional<SlotOccupancy> getSlotOccupancy(long slotId, long parkingLotId) {
		return slotOccupancyRepository.findFirstBySlotIdAndParkingLotIdOrderByIdDesc(slotId, parkingLotId);
	}

	public Optional<SlotOccupancy> getSlotOccupancyEndTimeNull(long slotId, long parkingLotId) {
		return slotOccupancyRepository.findBySlotIdAndParkingLotIdAndEndTimeNull(slotId, parkingLotId);
	}

	public SlotOccupancy save(SlotOccupancy slotOccupancy) {
		return slotOccupancyRepository.save(slotOccupancy);
	}

	public void updateSlotOccupancyEndTime(long slotId, long parkingLotId, OffsetDateTime endDateTime) {
		Optional<SlotOccupancy> slotOccupancyDetails = getSlotOccupancyEndTimeNull(slotId, parkingLotId);
		slotOccupancyDetails.ifPresent(slotOccupancy -> {
			slotOccupancy.setEndTime(endDateTime);
			save(slotOccupancy);
		});
	}

}
