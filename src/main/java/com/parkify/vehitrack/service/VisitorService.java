package com.parkify.vehitrack.service;

import com.parkify.vehitrack.dto.VisitorDTO;
import com.parkify.vehitrack.entity.VisitorType;
import com.parkify.vehitrack.entity.Visitors;

import java.util.List;

public interface VisitorService {

    Visitors addVisitor(Visitors visitors);

    VisitorDTO getVisitorByRegistrationNumber(String registrationNumber);

    Visitors updateVisitorExitTime(String vehicleRegistrationNumber);

    List<Visitors> getActiveVisitors(List<VisitorType> visitorTypes);

    void createDailyVisitorBackup();
}
