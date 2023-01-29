package com.ps.parking.lot.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ps.parking.lot.models.entities.Floor;

@Component
public interface FloorRepository extends JpaRepository<Floor, Long> {

    @EntityGraph(value = "ParkingLot.id", type = EntityGraphType.FETCH)
    public List<Floor> findParkingLotId(long parkingLotId);
    public Optional<Floor> findByMnemonic(int mnemonic);
}
