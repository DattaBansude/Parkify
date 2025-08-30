package com.parkify.vehitrack.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private long id;

    @NotBlank(message = "Registration number is mandatory")
    private String registrationNumber;

    @NotBlank(message = "Vehicle name is mandatory")
    @JsonProperty("vName")
    private String vName;

    private String color;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Vehicle type is mandatory")
    private VehicleType vehicleType;

    @Column(nullable = false)
    private LocalDateTime associationActivatedAt;

    private LocalDateTime associationDeactivatedAt;

    private boolean isVehicleActive;

    @ManyToOne()
    @JoinColumn(name = "resident_id",nullable = false)
    @JsonBackReference
    private Resident resident;

    @PrePersist
    public void prePersist() {
        this.associationActivatedAt = LocalDateTime.now();
        this.isVehicleActive = true;
    }
}
