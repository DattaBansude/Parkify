package com.parkify.vehitrack.serviceImpl;

import com.parkify.vehitrack.entity.Resident;
import com.parkify.vehitrack.repository.ResidentRepository;
import com.parkify.vehitrack.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidentServiceImpl implements ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    @Override
    public Resident createResident(Resident resident) {


        if (resident.getFName() == null || resident.getFName().trim().isEmpty()) {
            throw new IllegalArgumentException("First name is mandatory");
        }

        if (resident.getFlatNo() == null || !resident.getFlatNo().matches("^[A-Z]-\\d{1,3}$")) {
            throw new IllegalArgumentException("Flat number should be like A-123, B-23");
        }

        if (resident.getEmail() == null || !resident.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        String mobile = String.valueOf(resident.getMobileNo());
        if (mobile.length() != 10) {
            throw new IllegalArgumentException("Mobile number must be exactly 10 digits");
        }

        if (resident.getResidentType() == null) {
            throw new IllegalArgumentException("Resident type is mandatory (OWNER / TENANT)");
        }

        // If all checks pass → save
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
        // 1. Validate inputs (no numbers allowed)
        if (firstName != null && firstName.matches(".*\\d.*")) {
            throw new IllegalArgumentException("First name cannot contain numbers.");
        }
        if (lastName != null && lastName.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Last name cannot contain numbers.");
        }

        // 2. Query database (firstname, lastname, or both)
        List<Resident> residents = residentRepository.searchByName(firstName, lastName);

        // 3. If no data found
        if (residents.isEmpty()) {
            throw new IllegalArgumentException("No resident found with given input.");
        }

        return residents;
    }
}
