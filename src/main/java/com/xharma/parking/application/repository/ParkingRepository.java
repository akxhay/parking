package com.xharma.parking.application.repository;

import com.xharma.parking.application.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingLot, Long> {

    Optional<ParkingLot> findByName(String name);

}
