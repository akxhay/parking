package com.xharma.parking.application.repository;

import com.xharma.parking.application.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingLot, Long> {

}
