package com.ps.parking.lot.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ps.parking.lot.models.entities.SlotOccupancy;

@Component
public interface SlotOccupancyRepository extends JpaRepository<SlotOccupancy, Long> {
}
