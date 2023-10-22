package com.xharma.parking.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FloorRequestDto {

    @NotNull(message = "Floor name must not be null")
    private String name;

    private long smallSlots;

    private long mediumSlots;

    private long largeSlots;

    private long xlargeSlots;
}

