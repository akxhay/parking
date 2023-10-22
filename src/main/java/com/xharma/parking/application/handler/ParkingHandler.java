package com.xharma.parking.application.handler;

import com.xharma.parking.application.dto.DummyDto;
import com.xharma.parking.application.dto.ParkingLotRequestDto;
import com.xharma.parking.application.dto.ParkingLotPageDto;
import com.xharma.parking.application.dto.ParkingLotResponseDto;
import org.springframework.http.ResponseEntity;

public interface ParkingHandler {
    ResponseEntity<ParkingLotResponseDto> createParkingLot(ParkingLotRequestDto parkingLotRequestDto);

    ResponseEntity<ParkingLotPageDto> fetchParkingLots(int pageNumber, int pageSize);

    ResponseEntity<String> deleteParkingLot(Long id);

    ResponseEntity<String> createDummyParkingLot(DummyDto dummyDto);
}
