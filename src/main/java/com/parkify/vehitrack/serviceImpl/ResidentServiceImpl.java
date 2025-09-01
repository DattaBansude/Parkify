package com.parkify.vehitrack.serviceImpl;

import com.parkify.vehitrack.entity.Resident;
import com.parkify.vehitrack.helper.VehicleHelper;
import com.parkify.vehitrack.repository.ResidentRepository;
import com.parkify.vehitrack.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidentServiceImpl implements ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    @Override
    public Resident createResident(Resident resident) {

        if (resident.getVehicleList() != null && !resident.getVehicleList().isEmpty()) {
            resident.getVehicleList().forEach(vehicle -> {
                vehicle.setResident(resident);
                VehicleHelper.initializeVehicle(vehicle);
            });
        }

        // Optional: Check for duplicate email
        // if (residentRepository.findByEmail(resident.getEmail()).isPresent()) {
        //     throw new IllegalArgumentException("Email already exists");
        // }

        return residentRepository.save(resident);
    }
}
