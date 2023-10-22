package com.xharma.parking.application.dto;

import lombok.Data;

@Data
public class FloorRequestDto {

    private String name;

    private long smallSlots;

    private long mediumSlots;

    private long largeSlots;

    private long xlargeSlots;
}

