package com.xharma.parking.application.service;

import com.xharma.parking.application.converters.EntityDtoConverter;
import com.xharma.parking.application.dto.ParkingLotRequestDto;
import com.xharma.parking.application.dto.ParkingLotPageDto;
import com.xharma.parking.application.dto.ParkingLotResponseDto;
import com.xharma.parking.application.entity.ParkingLot;
import com.xharma.parking.application.repository.ParkingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ParkingServiceImpl implements ParkingService {


    @Autowired
    private ParkingRepository parkingRepository;

    @Override
    public void createParkingLots(List<ParkingLotRequestDto> parkingLotRequestDto) {
        log.info("saving new parking lots");
        parkingRepository.saveAll(parkingLotRequestDto.stream().map(EntityDtoConverter::parkingLotDtoToEntity).toList());
    }

    @Override
    public ParkingLotResponseDto createParkingLot(ParkingLotRequestDto parkingLotRequestDto) {
        log.info("saving new parking lot");
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
}
