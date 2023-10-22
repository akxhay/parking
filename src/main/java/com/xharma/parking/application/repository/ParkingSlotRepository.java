package com.xharma.parking.application.repository;

import com.xharma.parking.application.entity.ParkingSlot;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {


    @Query(value = "select * from parking_slot ps where floor_id in (select id from \"floor\" f where parking_lot_id =?1 ) and ps.slot_type =?2 and ps.is_occupied = false limit 1 ", nativeQuery = true)
    Optional<ParkingSlot> findAvailableSots(long parkingLotId, String slotType);


    @Modifying
    @Query(value = "update parking_slot set is_occupied = true where id  =?1", nativeQuery = true)
    int markOccupied(long parkingLotId);

}
