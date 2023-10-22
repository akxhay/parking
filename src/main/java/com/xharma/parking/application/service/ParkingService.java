package com.xharma.parking.application.service;

import com.xharma.parking.application.dto.AvailableParkingSlotDto;
import com.xharma.parking.application.dto.ParkingLotPageDto;
import com.xharma.parking.application.dto.ParkingLotRequestDto;
import com.xharma.parking.application.dto.ParkingLotResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;

public interface ParkingService {
    ParkingLotResponseDto createParkingLot(ParkingLotRequestDto parkingLotRequestDto);

    ParkingLotPageDto fetchParkingLots(PageRequest pageRequest);

    boolean deleteParkingLot(Long id);

    AvailableParkingSlotDto findSuitableSlot(Long id, String size);


    int releaseParkingLot(Long id, Long slotId);

    @Transactional
    int changeOccupied(Long id, boolean occupied);
}
