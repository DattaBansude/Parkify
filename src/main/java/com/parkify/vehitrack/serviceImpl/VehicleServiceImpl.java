package com.parkify.vehitrack.serviceImpl;


import com.parkify.vehitrack.dto.ResidentDTO;
import com.parkify.vehitrack.entity.Resident;
import com.parkify.vehitrack.entity.Vehicle;
import com.parkify.vehitrack.exception.InvalidRegistrationNumberException;
import com.parkify.vehitrack.exception.ResidentNotFoundException;
import com.parkify.vehitrack.exception.ValidationException;
import com.parkify.vehitrack.helper.VehicleHelper;
import com.parkify.vehitrack.repository.ResidentRepository;
import com.parkify.vehitrack.repository.VehicleRepository;
import com.parkify.vehitrack.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ResidentRepository residentRepository;

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {


        if (vehicle.getRegistrationNumber() == null || vehicle.getRegistrationNumber().trim().isEmpty()) {
            throw new ValidationException("Registration number is required!");
        }

        // Example: length must be exactly 10
        if (vehicle.getRegistrationNumber().length() != 10) {
            throw new ValidationException("Registration number must be exactly 10 characters long!");
        }

        if (vehicleRepository.existsByRegistrationNumber(vehicle.getRegistrationNumber())) {
            throw new ValidationException("Vehicle with this registration number already exists!");
        }

        // Check vehicle name
        if (vehicle.getVName() == null || vehicle.getVName().trim().isEmpty()) {
            throw new ValidationException("Vehicle name is required!");
        }

        // Check vehicle type
        if (vehicle.getVehicleType() == null) {
            throw new ValidationException("Vehicle type is required!");
        }

        // Check resident (vehicle must belong to a resident)
        if (vehicle.getResident() == null) {
            throw new ValidationException("Vehicle must be linked with a Resident!");
        }
        Resident resident = residentRepository.findById(vehicle.getResident().getId())
                .orElseThrow(() -> new ValidationException("Resident not found with ID: " + vehicle.getResident().getId()));

        vehicle.setResident(resident);

        VehicleHelper.initializeVehicle(vehicle);

        return vehicleRepository.save(vehicle);
    }


    @Override
    public ResidentDTO getResidentByRegistrationNumber(String registrationNumber) {
        // Validation
        // Validation
        if (registrationNumber == null || registrationNumber.trim().isEmpty()) {
            throw new InvalidRegistrationNumberException("Registration number is required!");
        }
        if (registrationNumber.length() != 10) {
            throw new InvalidRegistrationNumberException("Invalid registration number: must be exactly 10 characters long!");
        }

        // Fetch vehicle (unique result)
        Vehicle vehicle = vehicleRepository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new ResidentNotFoundException(
                        "No vehicle found with registration number: " + registrationNumber));

        Resident resident = vehicle.getResident();
        // Convert Resident to DTO
        return new ResidentDTO(
                vehicle.getResident().getId(),
                vehicle.getResident().getFName(),
                vehicle.getResident().getLName(),
                vehicle.getResident().getFlatNo(),
                vehicle.getResident().getMobileNo(),
                vehicle.getResident().getEmail(),
                vehicle.getResident().getResidentType()
        );
    }
}
