package com.xharma.parking.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLotPageDto {

    private int totalPages;

    private long totalElements;

    private List<ParkingLotResponseDto> parkingLots;
}

