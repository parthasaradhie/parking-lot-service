package com.ps.parking.lot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ps.parking.lot.dao.repositories.SlotRepository;
import com.ps.parking.lot.models.domain.enums.SlotSize;
import com.ps.parking.lot.models.entities.Slot;

@Component
public class SlotDao {
    @Autowired
    private SlotRepository slotRepository;
    private static final Pageable SINGLE_OBJECT_PAGESIZE = Pageable.ofSize(1);

    public void saveAll(List<Slot> slots) {
        slotRepository.saveAll(slots);
    }

    public Slot save(Slot slot) {
        return slotRepository.save(slot);
    }

    public void setSlotAsAvailable(Slot slot, long parkingLotId) {
        if (slot == null) {
            return;
        }
        slot.setAvailable(true);
        save(slot);
    }

    public Optional<Slot> findByMnemonicAndParkingLotId(String slotMnemonic, long parkingLotId) {
        return slotRepository.findByMnemonicAndParkingLot_Id(slotMnemonic, parkingLotId);
    }

    @Transactional
    public Optional<Slot> lockAndGetValidSlot(@Param("slotSize") SlotSize slotSize,
            @Param("parkingLotId") long parkingLotId) {
        return Optional
                .ofNullable(slotRepository.lockAndGetValidSlotsInternal(SINGLE_OBJECT_PAGESIZE, slotSize, parkingLotId))
                .filter(slot -> slot.size() > 0)
                .map(slot -> slot.get(0))
                .map(slot -> setSlotAvailabilityToFalse(slot))
                .map(this::save);
    }

    private Slot setSlotAvailabilityToFalse(Slot slot) {
        slot.setAvailable(false);
        return slot;
    }
}
