package com.xharma.parking.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ParkingLotRequestDto {

    @NotNull(message = "Parking lot name must not be null")
    private String name;

    @NotEmpty(message = "Floors must be present")
    @Valid
    private List<FloorRequestDto> floors;
}

