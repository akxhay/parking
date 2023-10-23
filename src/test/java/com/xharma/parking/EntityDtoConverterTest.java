package com.xharma.parking;

import com.xharma.parking.application.converters.EntityDtoConverter;
import com.xharma.parking.application.dto.FloorRequestDto;
import com.xharma.parking.application.dto.FloorResponseDto;
import com.xharma.parking.application.dto.ParkingLotResponseDto;
import com.xharma.parking.application.entity.Floor;
import com.xharma.parking.application.entity.ParkingLot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntityDtoConverterTest {


    @Test
    void testFloorDtoToEntity() {
        // Create a FloorRequestDto
        FloorRequestDto floorRequestDto = new FloorRequestDto();
        floorRequestDto.setName("Test Floor");
        floorRequestDto.setSmallSlots(10);

        // Call the conversion method
        Floor floor = EntityDtoConverter.floorDtoToEntity(floorRequestDto);

        // Assertions
        assertEquals(floorRequestDto.getName(), floor.getName());
        assertEquals(10, floor.getParkingSlots().size());
    }

    @Test
    void testParkingLotEntityToDto() {
        // Create a ParkingLot
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("Test Parking Lot");

        // Call the conversion method
        ParkingLotResponseDto parkingLotResponseDto = EntityDtoConverter.parkingLotEntityToDto(parkingLot);

        // Assertions
        assertEquals(parkingLot.getName(), parkingLotResponseDto.getName());
    }

    @Test
    void testFloorEntityToDto() {
        // Create a Floor
        Floor floor = new Floor();
        floor.setName("Test Floor");

        // Call the conversion method
        FloorResponseDto floorResponseDto = EntityDtoConverter.floorEntityToDto(floor);

        // Assertions
        assertEquals(floor.getName(), floorResponseDto.getName());
    }


}
