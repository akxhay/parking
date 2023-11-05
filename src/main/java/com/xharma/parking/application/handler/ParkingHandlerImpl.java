package com.xharma.parking.application.handler;

import com.xharma.parking.application.converters.EntityDtoConverter;
import com.xharma.parking.application.dto.*;
import com.xharma.parking.application.entity.ParkingSlot;
import com.xharma.parking.application.exceptions.ParkingException;
import com.xharma.parking.application.service.ParkingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class ParkingHandlerImpl implements ParkingHandler {

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private HttpServletRequest httpServletRequest;

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
            log.info("Could not delete parking slot");
            throw new ParkingException("Parking Slot was not deleted");
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
            log.info("Could not find parking slot");
            throw new ParkingException("Parking Slot is not available");
        }
        String numberPlate = httpServletRequest.getHeader("number-plate");

        if (!StringUtils.hasText(numberPlate)) {
            log.info("Number plate is not present in header");
            throw new ParkingException("Car cannot be parked without number plate");
        }
        String arrivedAt = httpServletRequest.getHeader("arrived-at");
        if (!StringUtils.hasText(arrivedAt) || !arrivedAt.matches("\\d*")) {
            log.info("Arrived At is not present in header");
            throw new ParkingException("Car arrival time is not present");
        }
        ParkingSlot parkingSlot = parkingService.parkInfo(numberPlate);
        if (parkingSlot != null) {
            String floorName = parkingSlot.getFloor().getName();
            String parkingLotName = parkingSlot.getFloor().getParkingLot().getName();
            String slotType = parkingSlot.getSlotType();
            int slotNumber = parkingSlot.getSlotNumber();

            log.info("Car is already parked");
            throw new ParkingException("Car is already parked \n" +
                    "Please find parking information below \n" +
                    "Parking Lot Name: " + parkingLotName + "\n" +
                    "floor Name: " + floorName + "\n" +
                    "Slot Type: " + slotType + "\n" +
                    "Slot Number: " + slotNumber);
        }
        int count = parkingService.park(availableParkingSlotDto.getSlotId(), numberPlate, Long.parseLong(arrivedAt));
        if (count == 0) {
            log.info("parking could not be marked");
            throw new ParkingException("Car cannot be parked at this moment, Please try again");
        }
        availableParkingSlotDto.setNumberPlate(numberPlate);
        availableParkingSlotDto.setArrivedAt(Long.parseLong(arrivedAt));
        return ResponseEntity.ok(availableParkingSlotDto);

    }

    @Override
    public ResponseEntity<String> releaseParkingSlot(Long id, Long slotId) {
        int count = parkingService.releaseParkingLot(id, slotId);
        if (count == 0) {
            log.info("Could not release parking slot");
            throw new ParkingException("Parking Slot could not be released");
        }
        return ResponseEntity.ok("Parking slot freed successfully");
    }
}
