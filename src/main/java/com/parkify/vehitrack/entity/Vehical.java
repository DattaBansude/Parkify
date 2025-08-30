package com.parkify.vehitrack.entity;


import jakarta.persistence.*;
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
public class Vehical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String registerationNumber;

    @Column(nullable = false)
    private String vName;

    private String color;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicalType vehicalType;

    @Column(nullable = false)
    private LocalDateTime associationActivatedAt;

    private LocalDateTime associationDeactivatedAt;

    @Column(nullable = false)
    private boolean isVehicalActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resident_id")
    private Resident resident;

}
