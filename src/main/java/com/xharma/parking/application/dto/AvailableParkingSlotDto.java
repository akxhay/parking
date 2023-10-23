package com.xharma.parking.application.dto;

import lombok.Data;

@Data

public class AvailableParkingSlotDto {

    private long slotId;

    private String slotType;

    private Integer slotNumber;

    private long floorId;

    private String floorName;

    private String numberPlate;

    private Long arrivedAt;
}

