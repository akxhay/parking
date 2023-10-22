package com.xharma.parking.application.handler;

import com.xharma.parking.application.converters.EntityDtoConverter;
import com.xharma.parking.application.dto.*;
import com.xharma.parking.application.service.ParkingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParkingHandlerImpl implements ParkingHandler {

    @Autowired
    private ParkingService parkingService;

    @Override
    public ResponseEntity<ParkingLotResponseDto> createParkingLot(ParkingLotRequestDto parkingLotRequestDto) {
        return ResponseEntity.ok(parkingService.createParkingLot(parkingLotRequestDto));
    }

    @Override
    public ResponseEntity<ParkingLotPageDto> fetchParkingLots(int pageNumber, int pageSize) {
        return ResponseEntity.ok(parkingService.fetchParkingLots(PageRequest.of(pageNumber, pageSize,
                Sort.Direction.DESC, "id")));
    }


    @Override
    public ResponseEntity<String> deleteParkingLot(Long id) {
        boolean deleted = parkingService.deleteParkingLot(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Parking lot deleted successfully");
    }

    @Override
    public ResponseEntity<String> createDummyParkingLot(DummyDto dummyDto) {
        EntityDtoConverter.dummyDtoToParkingLotDto(dummyDto).parallelStream().forEach(parkingService::createParkingLot);
        return ResponseEntity.ok("Dummy parking lots created successfully");
    }

    @Override
    public ResponseEntity<AvailableParkingSlotDto> getParkingSlot(Long id, String size) {
        AvailableParkingSlotDto availableParkingSlotDto = parkingService.findSuitableSlot(id, size);
        if (availableParkingSlotDto == null) {
            return ResponseEntity.notFound().build();
        }
        int count = parkingService.changeOccupied(availableParkingSlotDto.getSlotId(), true);
        if (count == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(availableParkingSlotDto);

    }

    @Override
    public ResponseEntity<String> releaseParkingSlot(Long id, Long slotId) {
        int count = parkingService.releaseParkingLot(id, slotId);
        if (count == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Parking slot freed successfully");
    }
}
