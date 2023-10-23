package com.xharma.parking;


import com.xharma.parking.application.dto.ParkingLotPageDto;
import com.xharma.parking.application.dto.ParkingLotRequestDto;
import com.xharma.parking.application.dto.ParkingLotResponseDto;
import com.xharma.parking.application.handler.ParkingHandlerImpl;
import com.xharma.parking.application.service.ParkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ParkingHandlerTest {

    @InjectMocks
    private ParkingHandlerImpl parkingHandler;

    @Mock
    private ParkingService parkingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks and inject them into parkingHandler
    }

    @Test
    void testCreateParkingLot() {
        ParkingLotRequestDto requestDto = new ParkingLotRequestDto();
        ResponseEntity<ParkingLotResponseDto> expectedResponse = ResponseEntity.ok(new ParkingLotResponseDto());

        when(parkingService.createParkingLot(requestDto)).thenReturn(new ParkingLotResponseDto());

        ResponseEntity<ParkingLotResponseDto> response = parkingHandler.createParkingLot(requestDto);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testFetchParkingLots() {
        int pageNumber = 1;
        int pageSize = 10;
        ResponseEntity<ParkingLotPageDto> expectedResponse = ResponseEntity.ok(new ParkingLotPageDto());

        when(parkingService.fetchParkingLots(PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id"))).thenReturn(new ParkingLotPageDto());

        ResponseEntity<ParkingLotPageDto> response = parkingHandler.fetchParkingLots(pageNumber, pageSize);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testDeleteParkingLot() {
        Long parkingLotId = 1L;
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Parking lot deleted successfully");

        when(parkingService.deleteParkingLot(parkingLotId)).thenReturn(true);

        ResponseEntity<String> response = parkingHandler.deleteParkingLot(parkingLotId);

        assertEquals(expectedResponse, response);
    }

    // Add more test cases for other methods as needed.
}
