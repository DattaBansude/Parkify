package com.parkify.vehitrack.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Visitors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private int id;

    private String visitorName;

    private String vehicleName;

    private String vehicleRegistrationNumber;

    private String visitPurpose;

    private LocalDateTime timeIn;

    private LocalDateTime timeOut;

    private long phoneNumber;

    private boolean isActiveVisitor;

    @Enumerated(EnumType.STRING)
    private VisitorType visitorType;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    @JsonBackReference
    private Resident resident;
}
