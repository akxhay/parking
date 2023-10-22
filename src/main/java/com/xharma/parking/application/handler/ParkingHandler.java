package com.xharma.parking.application.handler;

import com.xharma.parking.application.dto.*;
import org.springframework.http.ResponseEntity;

public interface ParkingHandler {
    ResponseEntity<ParkingLotResponseDto> createParkingLot(ParkingLotRequestDto parkingLotRequestDto);

    ResponseEntity<ParkingLotPageDto> fetchParkingLots(int pageNumber, int pageSize);

    ResponseEntity<String> deleteParkingLot(Long id);

    ResponseEntity<String> createDummyParkingLot(DummyDto dummyDto);

    ResponseEntity<AvailableParkingSlotDto> getParkingSlot(Long id, String size);

    ResponseEntity<String> releaseParkingSlot(Long id, Long slotId);
}
