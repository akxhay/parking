package com.xharma.parking.application.converters;

import com.xharma.parking.application.dto.*;
import com.xharma.parking.application.entity.Floor;
import com.xharma.parking.application.entity.ParkingLot;
import com.xharma.parking.application.entity.ParkingSlot;

import java.util.ArrayList;
import java.util.List;

public class EntityDtoConverter {
    public static ParkingLot parkingLotDtoToEntity(ParkingLotRequestDto parkingLotRequestDto) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(parkingLotRequestDto.getName());
        parkingLot.setFloors(parkingLotRequestDto.getFloors().stream().map(EntityDtoConverter::floorDtoToEntity).toList());
        parkingLot.getFloors().forEach(it -> it.setParkingLot(parkingLot));
        return parkingLot;
    }

    public static Floor floorDtoToEntity(FloorRequestDto floorRequestDto) {
        Floor floor = new Floor();
        floor.setName(floorRequestDto.getName());
        List<ParkingSlot> parkingSlotList = new ArrayList<>();
        parkingSlotList.addAll(getParkingSlots("s", floorRequestDto.getSmallSlots()));
        parkingSlotList.addAll(getParkingSlots("m", floorRequestDto.getMediumSlots()));
        parkingSlotList.addAll(getParkingSlots("l", floorRequestDto.getLargeSlots()));
        parkingSlotList.addAll(getParkingSlots("xl", floorRequestDto.getXlargeSlots()));
        parkingSlotList.forEach(it -> it.setFloor(floor));
        floor.setParkingSlots(parkingSlotList);
        return floor;
    }

    private static List<ParkingSlot> getParkingSlots(String slotType, long size) {
        List<ParkingSlot> parkingSlotList = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            ParkingSlot parkingSlot = new ParkingSlot();
            parkingSlot.setSlotType(slotType);
            parkingSlot.setSlotNumber(i);
            parkingSlot.setOccupied(false);
            parkingSlotList.add(parkingSlot);
        }
        return parkingSlotList;
    }

    public static ParkingLotResponseDto parkingLotEntityToDto(ParkingLot parkingLot) {
        ParkingLotResponseDto parkingLotResponseDto = new ParkingLotResponseDto();
        parkingLotResponseDto.setId(parkingLot.getId());
        parkingLotResponseDto.setName(parkingLot.getName());
        parkingLotResponseDto.setFloors(parkingLot.getFloors().stream().map(EntityDtoConverter::floorEntityToDto).toList());

        return parkingLotResponseDto;
    }

    public static FloorResponseDto floorEntityToDto(Floor floor) {
        FloorResponseDto floorResponseDto = new FloorResponseDto();
        floorResponseDto.setName(floor.getName());
        floorResponseDto.setParkingSlots(floor.getParkingSlots().stream().map(EntityDtoConverter::parkingSlotEntityToDto).toList());
        return floorResponseDto;
    }

    public static ParkingSlotDto parkingSlotEntityToDto(ParkingSlot parkingSlot) {
        ParkingSlotDto parkingSlotDto = new ParkingSlotDto();
        parkingSlotDto.setSlotType(parkingSlot.getSlotType());
        parkingSlotDto.setSlotNumber(parkingSlot.getSlotNumber());
        parkingSlotDto.setOccupied(parkingSlot.isOccupied());
        return parkingSlotDto;
    }

    public static List<ParkingLotRequestDto> dummyDtoToParkingLotDto(DummyDto dummyDto) {
        List<ParkingLotRequestDto> parkingLotRequestDtos = new ArrayList<>();
        for (int i = 1; i <= dummyDto.getParkingLots(); i++) {
            ParkingLotRequestDto parkingLotRequestDto = new ParkingLotRequestDto();
            parkingLotRequestDto.setName("Parking lot " + i);
            parkingLotRequestDto.setFloors(dummyDtoToFlorDto(dummyDto));
            parkingLotRequestDtos.add(parkingLotRequestDto);
        }
        return parkingLotRequestDtos;
    }

    public static List<FloorRequestDto> dummyDtoToFlorDto(DummyDto dummyDto) {
        List<FloorRequestDto> floorRequestDtos = new ArrayList<>();
        for (int i = 1; i <= dummyDto.getFloorsPerParkingLot(); i++) {
            FloorRequestDto floorRequestDto = new FloorRequestDto();
            floorRequestDto.setName("Floor " + i);
            floorRequestDto.setSmallSlots(dummyDto.getSmallSlotsPerFloor());
            floorRequestDto.setMediumSlots(dummyDto.getMediumSlotsPerFloor());
            floorRequestDto.setLargeSlots(dummyDto.getLargeSlotsPerFloor());
            floorRequestDto.setXlargeSlots(dummyDto.getXlargeSlotsPerFloor());
            floorRequestDtos.add(floorRequestDto);
        }
        return floorRequestDtos;
    }

    public static AvailableParkingSlotDto parkingSlotEntityToAvailableDto(ParkingSlot parkingSlot) {
        AvailableParkingSlotDto availableParkingSlotDto = new AvailableParkingSlotDto();
        availableParkingSlotDto.setSlotType(parkingSlot.getSlotType());
        availableParkingSlotDto.setSlotNumber(parkingSlot.getSlotNumber());
        availableParkingSlotDto.setSlotId(parkingSlot.getId());
        availableParkingSlotDto.setFloorId(parkingSlot.getFloor().getId());
        availableParkingSlotDto.setFloorName(parkingSlot.getFloor().getName());
        return availableParkingSlotDto;
    }

}
