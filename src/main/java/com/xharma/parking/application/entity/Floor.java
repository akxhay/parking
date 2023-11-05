package com.xharma.parking.application.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Data
@DynamicUpdate
@Entity
@Table(name = "floors")
public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;

    @JsonManagedReference
    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ParkingSlot> parkingSlots = new ArrayList<>();
}

