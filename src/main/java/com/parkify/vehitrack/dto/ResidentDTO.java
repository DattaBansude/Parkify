package com.parkify.vehitrack.dto;

import com.parkify.vehitrack.entity.ResidentType;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResidentDTO {

    private int id;
    private String fName;
    private String lName;
    private String flatNo;
    private long mobileNo;
    private String email;
    private ResidentType residentType;

}
