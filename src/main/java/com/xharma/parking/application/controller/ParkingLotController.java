package com.xharma.parking.application.controller;

import com.xharma.parking.application.constants.GenericConstants;
import com.xharma.parking.application.dto.*;
import com.xharma.parking.application.handler.ParkingHandler;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "parking/")
@Slf4j
public class ParkingLotController {
    @Autowired
    private ParkingHandler parkingHandler;

    @GetMapping(value = "greet")
    public @ResponseBody ResponseEntity<String> greet() {
        log.info("Inside greet");
        return ResponseEntity.ok(GenericConstants.GREET);
    }

    @PostMapping(value = "lot/dummy")
    public @ResponseBody ResponseEntity<String> createDummyParkingLot(@RequestBody DummyDto dummyDto) {
        log.info("Inside createDummyParkingLot, dummyDto : {}", dummyDto);
        return parkingHandler.createDummyParkingLot(dummyDto);
    }

    @PostMapping(value = "lot")
    public @ResponseBody ResponseEntity<ParkingLotResponseDto> createParkingLot(@RequestBody @Valid ParkingLotRequestDto parkingLotRequestDto) {
        log.info("Inside createParkingLot, parkingLotDto : {}", parkingLotRequestDto);
        return parkingHandler.createParkingLot(parkingLotRequestDto);
    }

    @GetMapping(value = "lot")
    public @ResponseBody ResponseEntity<ParkingLotPageDto> fetchParkingLots(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        log.info("Inside fetchParkingLots with pageNumber {}, pageSize {}", pageNumber, pageSize);
        return parkingHandler.fetchParkingLots(pageNumber, pageSize);
    }

    @DeleteMapping(value = "lot/{parking_lot_id}")
    public @ResponseBody ResponseEntity<String> deleteParkingLot(@PathVariable("parking_lot_id") Long id) {
        log.info("Inside deleteParkingLot, id : {}", id);
        return parkingHandler.deleteParkingLot(id);
    }

    @GetMapping(value = "getslot/{parking_lot_id}/{size}")
    public @ResponseBody ResponseEntity<AvailableParkingSlotDto> getParkingSlot(@PathVariable("parking_lot_id") Long id, @PathVariable("size") String type) {
        log.info("Inside getParkingSlot, parking_lot_id : {}, size: {}", id, type);
        return parkingHandler.getParkingSlot(id, type);
    }

    @PutMapping(value = "releaseslot/{parking_lot_id}/{slot_id}")
    public @ResponseBody ResponseEntity<String> releaseParkingSlot(@PathVariable("parking_lot_id") Long id, @PathVariable("slot_id") Long slotId) {
        log.info("Inside releaseParkingSlot, parking_lot_id : {}, slotId: {}", id, slotId);
        return parkingHandler.releaseParkingSlot(id, slotId);
    }
}
