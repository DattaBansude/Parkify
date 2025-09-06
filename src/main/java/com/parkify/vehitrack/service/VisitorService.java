package com.parkify.vehitrack.service;

import com.parkify.vehitrack.dto.VisitorDTO;
import com.parkify.vehitrack.entity.Visitors;

import java.time.LocalDateTime;

public interface VisitorService {

    Visitors addVisitor(Visitors visitors);

    VisitorDTO getVisitorByRegistrationNumber(String registrationNumber);

    Visitors updateVisitorExitTime(String vehicleRegistrationNumber);
}
