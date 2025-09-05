package com.parkify.vehitrack.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VisitorDTO {

    private String visitorName;
    private String vehicleName;
    private String registrationNumber;
    private String visitPurpose;
    private String timeIn;
    private String visitorType;
    private boolean activeVisitor;

    private ResidentDTO resident; // nested resident details
}
