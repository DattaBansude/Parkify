package com.parkify.vehitrack.serviceImpl;

import com.parkify.vehitrack.entity.Resident;
import com.parkify.vehitrack.exception.ResidentNotFoundException;
import com.parkify.vehitrack.helper.VehicleHelper;
import com.parkify.vehitrack.repository.ResidentRepository;
import com.parkify.vehitrack.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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

    @Override
    public List<Resident> getAllResidents() {

        List<Resident> residentList = residentRepository.findAll();

        return residentList;
    }

    @Override
    public List<Resident> getResidentByName(String firstName, String lastName) {

        // Validation: no numeric values
        if ((firstName != null && firstName.matches(".*\\d.*")) ||
                (lastName != null && lastName.matches(".*\\d.*"))) {
            throw new IllegalArgumentException("Names cannot contain numeric values");
        }

        List<Resident> residents = residentRepository.findByFirstAndLastName(firstName, lastName);

        if (residents.isEmpty()) {
            throw new ResidentNotFoundException("No resident found with the provided details");
        }

        return residents;
    }
}
