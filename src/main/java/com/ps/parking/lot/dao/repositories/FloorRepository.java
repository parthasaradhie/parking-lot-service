package com.ps.parking.lot.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ps.parking.lot.models.entities.Floor;
import com.ps.parking.lot.models.entities.ParkingLot;

@Component
public interface FloorRepository extends JpaRepository<Floor, Long> {

	public Optional<Floor> findByMnemonic(int mnemonic);

	@Query("FROM Floor FR WHERE FR.parkingLot=:parkingLot")
	public List<Floor> findByParkingLotId(ParkingLot parkingLot);
}
