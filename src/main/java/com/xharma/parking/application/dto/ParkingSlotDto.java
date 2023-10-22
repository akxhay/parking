package com.xharma.parking.application.dto;

import lombok.Data;

@Data

public class ParkingSlotDto {

    private long id;

    private String slotType;

    private Integer slotNumber;

    private boolean isOccupied;
}

