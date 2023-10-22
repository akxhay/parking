package com.xharma.parking.application.dto;

import lombok.Data;

@Data
public class DummyDto {

    private long parkingLots;

    private long floorsPerParkingLot;

    private long smallSlotsPerFloor;

    private long mediumSlotsPerFloor;

    private long largeSlotsPerFloor;

    private long xLargeSlotsPerFloor;
}

