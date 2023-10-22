package com.xharma.parking.application.service;

import com.xharma.parking.application.dto.AvailableParkingSlotDto;
import com.xharma.parking.application.dto.ParkingLotPageDto;
import com.xharma.parking.application.dto.ParkingLotRequestDto;
import com.xharma.parking.application.dto.ParkingLotResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ParkingService {
    void createParkingLots(List<ParkingLotRequestDto> parkingLotRequestDto);

    ParkingLotResponseDto createParkingLot(ParkingLotRequestDto parkingLotRequestDto);

    ParkingLotPageDto fetchParkingLots(PageRequest pageRequest);

    void deleteParkingLot(Long id);

    AvailableParkingSlotDto findSuitableSlot(Long id, String size);

    int markSlotOccupied(Long id);
}
