package com.xharma.parking.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class FloorResponseDto {

    private String name;

    private List<ParkingSlotDto> parkingSlots;
}

