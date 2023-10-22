package com.xharma.parking.application.handler;

import com.xharma.parking.application.converters.EntityDtoConverter;
import com.xharma.parking.application.dto.DummyDto;
import com.xharma.parking.application.dto.ParkingLotPageDto;
import com.xharma.parking.application.dto.ParkingLotRequestDto;
import com.xharma.parking.application.dto.ParkingLotResponseDto;
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
        parkingService.deleteParkingLot(id);
        return ResponseEntity.ok("Parking lot deleted successfully");
    }

    @Override
    public ResponseEntity<String> createDummyParkingLot(DummyDto dummyDto) {
        parkingService.createParkingLots(EntityDtoConverter.dummyDtoToParkingLotDto(dummyDto));
        return ResponseEntity.ok("Dummy parking lots created successfully");
    }
}
