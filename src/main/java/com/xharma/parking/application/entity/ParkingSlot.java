package com.xharma.parking.application.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Data
@DynamicUpdate
@Entity
@Table(name = "parking_slots")
@EnableJpaAuditing
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "floor_id")
    private Floor floor;

    @Column(name = "slot_type", nullable = false)
    private String slotType;

    @Column(name = "slot_number", nullable = false)
    private Integer slotNumber;

    @Column(name = "occupied", nullable = false)
    private boolean occupied = false;

    @Column(name = "number_plate", unique = true)
    private String numberPlate;

    @Column(name = "arrived_at")
    private Long arrivedAt;
}

