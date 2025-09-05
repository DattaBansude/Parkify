package com.parkify.vehitrack.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$",
            message = "Registration number must match format e.g., MH12AB1234")
    private String registrationNumber;

    @JsonProperty("vName")
    private String vName;

    private String color;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Column(nullable = false)
    @Schema(hidden = true)
    private LocalDateTime associationActivatedAt;

    @Schema(hidden = true)
    private LocalDateTime associationDeactivatedAt;


    private boolean isVehicleActive;

    @ManyToOne()
    @JoinColumn(name = "resident_id", nullable = false)
    @JsonBackReference
    private Resident resident;

}
