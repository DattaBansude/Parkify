package com.parkify.vehitrack.service;

import com.parkify.vehitrack.dto.ResidentDTO;
import com.parkify.vehitrack.entity.Resident;
import com.parkify.vehitrack.entity.Vehicle;

public interface VehicleService {

    Vehicle createVehicle(Vehicle vehicle);

//    Resident getResidentByRegistrationNumber(String registrationNumber);

    ResidentDTO getResidentByRegistrationNumber(String registrationNumber);
}
