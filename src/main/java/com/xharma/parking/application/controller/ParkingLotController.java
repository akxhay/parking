package com.xharma.parking.application.controller;

import com.xharma.parking.application.constants.RestURIConstants;
import com.xharma.parking.application.dto.DummyDto;
import com.xharma.parking.application.dto.ParkingLotPageDto;
import com.xharma.parking.application.dto.ParkingLotRequestDto;
import com.xharma.parking.application.dto.ParkingLotResponseDto;
import com.xharma.parking.application.handler.ParkingHandler;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = RestURIConstants.PARKING_BASE)
@Slf4j
public class ParkingLotController {
    @Autowired
    private ParkingHandler parkingHandler;

    @GetMapping(value = RestURIConstants.GREET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<String> greet() {
        log.info("Inside greet");
        return ResponseEntity.ok("Welcome to our parking lot management system");
    }

    @PostMapping(value = RestURIConstants.CREATE_DUMMY_PARKING_LOT_V1)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public @ResponseBody ResponseEntity<String> createDummyParkingLot(@Valid @RequestBody DummyDto dummyDto) {
        log.info("Inside createDummyParkingLot, dummyDto : {}", dummyDto);
        return parkingHandler.createDummyParkingLot(dummyDto);
    }

    @PostMapping(value = RestURIConstants.CREATE_PARKING_LOT_V1)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<ParkingLotResponseDto> createParkingLot(@Valid @RequestBody ParkingLotRequestDto parkingLotRequestDto) {
        log.info("Inside createParkingLot, parkingLotDto : {}", parkingLotRequestDto);
        return parkingHandler.createParkingLot(parkingLotRequestDto);
    }

    @GetMapping(value = RestURIConstants.FETCH_PARKING_LOTS_V1)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<ParkingLotPageDto> fetchParkingLots(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        log.info("Inside fetchParkingLots");
        return parkingHandler.fetchParkingLots(pageNumber, pageSize);
    }

    @DeleteMapping(value = RestURIConstants.DELETE_PARKING_LOT_V1)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody ResponseEntity<String> deleteParkingLot(@PathVariable("id") Long id) {
        log.info("Inside deleteParkingLot, id : {}", id);
        return parkingHandler.deleteParkingLot(id);
    }

}
