package com.ps.parking.lot.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.hibernate.LockOptions;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ps.parking.lot.models.domain.enums.SlotSize;
import com.ps.parking.lot.models.entities.Slot;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;

@Component
public interface SlotRepository extends JpaRepository<Slot, Long> {

	public Optional<Slot> findByMnemonicAndParkingLot_Id(String slotMnemonic, long parkingLotId);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@QueryHints({ @QueryHint(name = "jakarta.persistence.lock.timeout", value = LockOptions.SKIP_LOCKED + "") })
	@Query("SELECT LOT FROM Slot LOT WHERE LOT.parkingLot.id=:parkingLotId AND LOT.slotSize>=:slotSize AND LOT.isAvailable=true ORDER BY LOT.slotSize ASC")
	public List<Slot> lockAndGetValidSlotsInternal(Pageable page, @Param("slotSize") SlotSize slotSize,
			@Param("parkingLotId") long parkingLotId);
}
