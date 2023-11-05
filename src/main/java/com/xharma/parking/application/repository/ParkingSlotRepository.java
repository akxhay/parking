package com.xharma.parking.application.repository;

import com.xharma.parking.application.entity.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {


    @Query(value = "select * from parking_slots ps where floor_id in (select id from floors f where parking_lot_id =?1 ) and ps.slot_type =?2 and ps.occupied = false order by id limit 1 ", nativeQuery = true)
    Optional<ParkingSlot> findAvailableSots(long parkingLotId, String slotType);


    @Modifying
    @Query(value = "update parking_slots set occupied = false, number_plate =null, arrived_at = null  where id =?1", nativeQuery = true)
    int unPark(long slotId);

    @Modifying
    @Query(value = "update parking_slots set occupied = true, number_plate =?2, arrived_at =?3  where id =?1", nativeQuery = true)
    int park(long slotId, String numberPlate, long nanoTime);

    Optional<ParkingSlot> findByNumberPlate(String numberPlate);
}
