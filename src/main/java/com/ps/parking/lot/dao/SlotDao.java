package com.ps.parking.lot.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ps.parking.lot.dao.repositories.SlotRepository;
import com.ps.parking.lot.models.domain.enums.SlotType;
import com.ps.parking.lot.models.entities.Slot;

@Component
public class SlotDao {
    @Autowired
    private SlotRepository slotRepository;
    private static final Pageable SINGLE_OBJECT_PAGESIZE = Pageable.ofSize(1);

    public Slot lockAndGetValidSlots(@Param("slotType") SlotType slotType,
            @Param("parkingLotId") int parkingLotId) {
        return Optional
                .ofNullable(slotRepository.lockAndGetValidSlotsInternal(SINGLE_OBJECT_PAGESIZE, slotType, parkingLotId))
                .filter(lots -> lots.size() > 0)
                .map($ -> $.get(0)).orElse(null);
    }
}
