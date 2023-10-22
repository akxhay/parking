package com.xharma.parking.application.service;

import com.xharma.parking.application.dto.ParkingLotPageDto;
import com.xharma.parking.application.dto.ParkingLotRequestDto;
import com.xharma.parking.application.dto.ParkingLotResponseDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ParkingService {
    void createParkingLots(List<ParkingLotRequestDto> parkingLotRequestDto);

    ParkingLotResponseDto createParkingLot(ParkingLotRequestDto parkingLotRequestDto);

    ParkingLotPageDto fetchParkingLots(PageRequest pageRequest);

    void deleteParkingLot(Long id);
}
