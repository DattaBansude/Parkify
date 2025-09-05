package com.parkify.vehitrack.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
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

    @Column(name = "f_name", columnDefinition = "VARCHAR(255)")
    @JsonProperty("fName")
    private String fName;

    @Column(name = "l_name", columnDefinition = "VARCHAR(255)")
    @JsonProperty("lName")
    private String lName;

    private String flatNo;

    private long mobileNo;

    private String email;

    @Enumerated(EnumType.STRING)
    private ResidentType residentType;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Vehicle> vehicleList = new ArrayList<>();

}
