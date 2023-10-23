package com.xharma.parking;

import com.xharma.parking.application.dto.ParkingLotPageDto;
import com.xharma.parking.application.dto.ParkingLotRequestDto;
import com.xharma.parking.application.dto.ParkingLotResponseDto;
import com.xharma.parking.application.entity.ParkingLot;
import com.xharma.parking.application.exceptions.ParkingException;
import com.xharma.parking.application.repository.ParkingRepository;
import com.xharma.parking.application.repository.ParkingSlotRepository;
import com.xharma.parking.application.service.ParkingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class ParkingServiceTest {

    @InjectMocks
    private ParkingServiceImpl parkingService;

    @Mock
    private ParkingRepository parkingRepository;

    @Mock
    private ParkingSlotRepository parkingSlotRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks and inject them into parkingService
    }

    @Test
    void testCreateParkingLot() {
        ParkingLotRequestDto requestDto = new ParkingLotRequestDto();
        requestDto.setName("Test Parking Lot");

        when(parkingRepository.findByName(requestDto.getName())).thenReturn(Optional.empty());

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(requestDto.getName());

        when(parkingRepository.save(any(ParkingLot.class))).thenReturn(parkingLot);

        requestDto.setFloors(new ArrayList<>());

        ParkingLotResponseDto responseDto = parkingService.createParkingLot(requestDto);

        assertNotNull(responseDto);
        assertEquals(requestDto.getName(), responseDto.getName());
    }

    @Test
    void testCreateParkingLotWhenParkingLotExists() {
        ParkingLotRequestDto requestDto = new ParkingLotRequestDto();
        requestDto.setName("Test Parking Lot");

        when(parkingRepository.findByName(requestDto.getName())).thenReturn(Optional.of(new ParkingLot()));

        assertThrows(ParkingException.class, () -> parkingService.createParkingLot(requestDto));
    }

    @Test
    void testFetchParkingLots() {
        PageRequest pageRequest = PageRequest.of(0, 10);

        Page<ParkingLot> parkingLots = new PageImpl<>(Collections.singletonList(new ParkingLot()));

        when(parkingRepository.findAll(pageRequest)).thenReturn(parkingLots);

        ParkingLotPageDto pageDto = parkingService.fetchParkingLots(pageRequest);

        assertNotNull(pageDto);
        assertEquals(1, pageDto.getTotalElements());
    }

    @Test
    void testDeleteParkingLot() {
        Long parkingLotId = 1L;

        when(parkingRepository.findById(parkingLotId)).thenReturn(Optional.of(new ParkingLot()));

        assertTrue(parkingService.deleteParkingLot(parkingLotId));
    }

    @Test
    void testDeleteParkingLotWhenNotFound() {
        Long parkingLotId = 1L;

        when(parkingRepository.findById(parkingLotId)).thenReturn(Optional.empty());

        assertFalse(parkingService.deleteParkingLot(parkingLotId));
    }

    // Add more test cases for other methods as needed.
}
