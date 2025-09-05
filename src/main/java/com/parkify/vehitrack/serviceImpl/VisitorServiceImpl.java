package com.parkify.vehitrack.serviceImpl;

import com.parkify.vehitrack.entity.Resident;
import com.parkify.vehitrack.entity.Visitors;
import com.parkify.vehitrack.exception.ValidationException;
import com.parkify.vehitrack.helper.VisitorHelper;
import com.parkify.vehitrack.repository.ResidentRepository;
import com.parkify.vehitrack.repository.VisitorRepository;
import com.parkify.vehitrack.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private ResidentRepository residentRepository;


    @Override
    public Visitors addVisitor(Visitors visitors) {

        // Validate visitor name
        if (visitors.getVisitorName() == null || visitors.getVisitorName().trim().isEmpty()) {
            throw new ValidationException("Visitor name is required!");
        }

        // Validate registration number
        if (visitors.getVehicalRegisterationNumber() == null || visitors.getVehicalRegisterationNumber().trim().isEmpty()) {
            throw new ValidationException("Vehicle registration number is required!");
        }
        if (visitors.getVehicalRegisterationNumber().length() != 10) {
            throw new ValidationException("Vehicle registration number must be exactly 10 characters!");
        }

        // Validate phone number
        String phoneStr = String.valueOf(visitors.getPhoneNumber());
        if (phoneStr.length() != 10) {
            throw new ValidationException("Phone number must be exactly 10 digits!");
        }

        // Validate visit purpose
        if (visitors.getVisitPurpose() == null || visitors.getVisitPurpose().trim().isEmpty()) {
            throw new ValidationException("Visit purpose is required!");
        }

        // Validate resident (visitor must be linked to resident)
        if (visitors.getResident() == null || visitors.getResident().getId() == 0) {
            throw new ValidationException("Visitor must be linked to a Resident!");
        }
        Resident resident = residentRepository.findById(visitors.getResident().getId())
                .orElseThrow(() -> new ValidationException("Resident not found with ID: " + visitors.getResident().getId()));

        visitors.setResident(resident);

        VisitorHelper.setEntryTime(visitors);
        // Validate timeIn (entry time required)
        if (visitors.getTimeIn() == null) {
            throw new ValidationException("Visitor entry time (timeIn) is required!");
        }
        Visitors visitor = visitorRepository.save(visitors);
        return visitor;
    }
}
