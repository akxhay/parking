package com.xharma.parking.application.service;

import com.xharma.parking.application.converters.EntityDtoConverter;
import com.xharma.parking.application.dto.AvailableParkingSlotDto;
import com.xharma.parking.application.dto.ParkingLotPageDto;
import com.xharma.parking.application.dto.ParkingLotRequestDto;
import com.xharma.parking.application.dto.ParkingLotResponseDto;
import com.xharma.parking.application.entity.ParkingLot;
import com.xharma.parking.application.entity.ParkingSlot;
import com.xharma.parking.application.repository.ParkingRepository;
import com.xharma.parking.application.repository.ParkingSlotRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.xharma.parking.application.converters.EntityDtoConverter.parkingSlotEntityToAvailableDto;

@Service
@Slf4j
public class ParkingServiceImpl implements ParkingService {


    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Override
    public void createParkingLots(List<ParkingLotRequestDto> parkingLotRequestDto) {
        log.info("saving new parking lots");
        parkingRepository.saveAll(parkingLotRequestDto.stream().map(EntityDtoConverter::parkingLotDtoToEntity).toList());
    }

    @Override
    public ParkingLotResponseDto createParkingLot(ParkingLotRequestDto parkingLotRequestDto) {
        log.info("saving new parking lot , name : {}", parkingLotRequestDto.getName());
        ParkingLot parkingLot = EntityDtoConverter.parkingLotDtoToEntity(parkingLotRequestDto);
        ParkingLot persistedEntity = parkingRepository.save(parkingLot);
        return EntityDtoConverter.parkingLotEntityToDto(persistedEntity);
    }

    @Override
    public ParkingLotPageDto fetchParkingLots(PageRequest pageRequest) {
        Page<ParkingLot> parkingLots = parkingRepository.findAll(pageRequest);
        return new ParkingLotPageDto(parkingLots.getTotalPages(), parkingLots.getTotalElements(), parkingLots.get().map(EntityDtoConverter::parkingLotEntityToDto).toList());
    }

    @Override
    public void deleteParkingLot(Long id) {
        parkingRepository.deleteById(id);
    }

    @Override
    public AvailableParkingSlotDto findSuitableSlot(Long id, String size) {
        switch (size) {
            case "s": {
                Optional<ParkingSlot> smallParkingSlot = parkingSlotRepository.findAvailableSots(id, "s");
                if (smallParkingSlot.isEmpty()) {
                    log.info("Small slot is not present");
                } else {
                    return parkingSlotEntityToAvailableDto(smallParkingSlot.get());
                }
            }
            case "m": {
                Optional<ParkingSlot> mediumParkingSlot = parkingSlotRepository.findAvailableSots(id, "m");
                if (mediumParkingSlot.isEmpty()) {
                    log.info("Medium slot is not present");
                } else {
                    return parkingSlotEntityToAvailableDto(mediumParkingSlot.get());
                }
            }
            case "l": {
                Optional<ParkingSlot> largeParkingSlot = parkingSlotRepository.findAvailableSots(id, "l");
                if (largeParkingSlot.isEmpty()) {
                    log.info("Large slot is not present");
                } else {
                    return parkingSlotEntityToAvailableDto(largeParkingSlot.get());
                }
            }
            case "xl": {
                Optional<ParkingSlot> xlargeParkingSlot = parkingSlotRepository.findAvailableSots(id, "xl");
                if (xlargeParkingSlot.isEmpty()) {
                    log.info("X-Large slot is not present");
                } else {
                    return parkingSlotEntityToAvailableDto(xlargeParkingSlot.get());
                }
            }
            default: {
                return null;
            }
        }
    }

    @Transactional
    @Override
    public int markSlotOccupied(Long id) {
        return parkingSlotRepository.markOccupied(id);
    }


}
