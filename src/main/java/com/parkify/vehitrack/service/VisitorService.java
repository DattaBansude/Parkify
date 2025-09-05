package com.parkify.vehitrack.service;

import com.parkify.vehitrack.dto.VisitorDTO;
import com.parkify.vehitrack.entity.Visitors;

public interface VisitorService {

    Visitors addVisitor(Visitors visitors);

    VisitorDTO getVisitorByRegistrationNumber(String registrationNumber);
}
