package com.ps.parking.lot.dao.repositories;

import java.util.List;

import org.hibernate.LockOptions;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ps.parking.lot.models.domain.enums.SlotType;
import com.ps.parking.lot.models.entities.Slot;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;

@Component
public interface SlotRepository extends JpaRepository<Slot, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({ @QueryHint(name = "javax.persistence.lock.timeout", value = LockOptions.SKIP_LOCKED + "") })
    @Query("SELECT LOT FROM Slot LOT WHERE LOT.parkingLot.id=:parkingLotId AND LOT.slotType>=:slotType GROUP BY LOT.slotType ORDER BY LOT.slotType DESC")
    public List<Slot> lockAndGetValidSlotsInternal(Pageable page, @Param("slotType") SlotType slotType,
            @Param("parkingLotId") int parkingLotId);
}
