package com.parkify.vehitrack.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private int id;

    @NotBlank(message = "First name is mandatory")
    @JsonProperty("fName")
    private String fName;

    @JsonProperty("lName")
    private String lName;

    @NotBlank(message = "Flat number is mandatory")
    @Pattern(regexp = "^[A-Z]-\\d{1,3}$", message = "Flat number should be like A-123, B-23")
    private String flatNo;

    @NotNull(message = "Mobile number is mandatory")
    private long mobileNo;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Resident type is mandatory")
    private ResidentType residentType;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Vehicle> vehicleList = new ArrayList<>();

}
