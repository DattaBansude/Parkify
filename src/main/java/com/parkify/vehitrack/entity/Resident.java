package com.parkify.vehitrack.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private String fName;

    private String lName;

    @Column(nullable = false)
    private String flatNo;

    @Column(nullable = false)
    private long mobileNo;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResidentType residentType;

    private ArrayList<Vehical> vehicalList;

}
