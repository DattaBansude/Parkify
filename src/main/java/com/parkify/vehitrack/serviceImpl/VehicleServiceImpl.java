package com.parkify.vehitrack.serviceImpl;


import com.parkify.vehitrack.entity.Resident;
import com.parkify.vehitrack.entity.Vehicle;
import com.parkify.vehitrack.helper.VehicleHelper;
import com.parkify.vehitrack.repository.ResidentRepository;
import com.parkify.vehitrack.repository.VehicleRepository;
import com.parkify.vehitrack.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ResidentRepository residentRepository;

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        // Rule 1: Registration number cannot be null/empty
        if (vehicle.getRegistrationNumber() == null || vehicle.getRegistrationNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle registration number cannot be empty.");
        }

        if (vehicle.getResident() == null || vehicle.getResident().getId() == 0) {
            throw new IllegalArgumentException("Vehicle must be associated with a valid residence.");
        }
        // Validate residence exists
        Resident residence = residentRepository.findById(vehicle.getResident().getId())
                .orElseThrow(() -> new IllegalArgumentException("Residence does not exist."));

        vehicle.setResident(residence);

        VehicleHelper.initializeVehicle(vehicle);

        return vehicleRepository.save(vehicle);
    }
}
