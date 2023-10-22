package com.xharma.parking.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ParkingLotResponseDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private List<FloorResponseDto> floors;
}

