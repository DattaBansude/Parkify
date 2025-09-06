package com.parkify.vehitrack.serviceImpl;

import com.parkify.vehitrack.dto.ResidentDTO;
import com.parkify.vehitrack.dto.VisitorDTO;
import com.parkify.vehitrack.entity.Resident;
import com.parkify.vehitrack.entity.VisitorType;
import com.parkify.vehitrack.entity.Visitors;
import com.parkify.vehitrack.exception.ValidationException;
import com.parkify.vehitrack.helper.VisitorHelper;
import com.parkify.vehitrack.repository.ResidentRepository;
import com.parkify.vehitrack.repository.VisitorRepository;
import com.parkify.vehitrack.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        if (visitors.getVehicleRegistrationNumber() == null || visitors.getVehicleRegistrationNumber().trim().isEmpty()) {
            throw new ValidationException("Vehicle registration number is required!");
        }
        if (visitors.getVehicleRegistrationNumber().length() != 10) {
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

        // Validate resident
        if (visitors.getResident() == null || visitors.getResident().getId() == 0) {
            throw new ValidationException("Visitor must be linked to a Resident!");
        }
        Resident resident = residentRepository.findById(visitors.getResident().getId())
                .orElseThrow(() -> new ValidationException("Resident not found with ID: " + visitors.getResident().getId()));

        visitors.setResident(resident);

        VisitorHelper.setEntryTime(visitors);
        if (visitors.getTimeIn() == null) {
            throw new ValidationException("Visitor entry time (timeIn) is required!");
        }

        return visitorRepository.save(visitors);
    }

    @Override
    public VisitorDTO getVisitorByRegistrationNumber(String registrationNumber) {

        if (registrationNumber == null || registrationNumber.trim().isEmpty()) {
            throw new ValidationException("Registration number is required!");
        }
        if (registrationNumber.length() != 10) {
            throw new ValidationException("Invalid registration number: must be 10 characters long!");
        }

        List<Visitors> visitors = visitorRepository.findByVehicleRegistrationNumber(registrationNumber);

        if (visitors.isEmpty()) {
            throw new ValidationException("No visitor found with registration number: " + registrationNumber);
        }

        Visitors visitor = visitors.get(0);

        ResidentDTO residentDTO = new ResidentDTO(
                visitor.getResident().getId(),
                visitor.getResident().getFName(),
                visitor.getResident().getLName(),
                visitor.getResident().getFlatNo(),
                visitor.getResident().getMobileNo(),
                visitor.getResident().getEmail(),
                visitor.getResident().getResidentType()
        );

        return new VisitorDTO(
                visitor.getVisitorName(),
                visitor.getVehicleName(),
                visitor.getVehicleRegistrationNumber(),
                visitor.getVisitPurpose(),
                visitor.getTimeIn() != null ? visitor.getTimeIn().toString() : null,
                visitor.getVisitorType() != null ? visitor.getVisitorType().name() : null,
                visitor.isActiveVisitor(),
                residentDTO
        );
    }

    @Override
    public Visitors updateVisitorExitTime(String vehicleRegistrationNumber) {
        if (vehicleRegistrationNumber == null || vehicleRegistrationNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle registration number is required!");
        }

        if (vehicleRegistrationNumber.length() != 10) {
            throw new IllegalArgumentException("Invalid registration number: must be 10 characters long!");
        }

        Visitors visitor = visitorRepository
                .findTopByVehicleRegistrationNumberOrderByTimeInDesc(vehicleRegistrationNumber)
                .orElseThrow(() -> new RuntimeException("No active visitor found with vehicle number: " + vehicleRegistrationNumber));

        if (visitor.getTimeOut() != null) {
            throw new RuntimeException("Visitor already has an exit time recorded!");
        }

        visitor.setTimeOut(VisitorHelper.resolveExitTime(null));
        visitor.setActiveVisitor(false);

        // Use helper method
        visitor.setVisitDuration(VisitorHelper.calculateDuration(visitor.getTimeIn(), visitor.getTimeOut()));

        return visitorRepository.save(visitor);
    }

    @Override
    public List<Visitors> getActiveVisitors(List<VisitorType> visitorTypes) {

        List<Visitors> visitors;

        if (visitorTypes == null || visitorTypes.isEmpty()) {
            visitors = visitorRepository.getActiveVisitors();
        } else {
            visitors = visitorRepository.getActiveVisitorsByTypes(visitorTypes);
        }

        return visitors.stream()
                .map(v -> {
                    Visitors minimal = new Visitors();
                    minimal.setId(v.getId());
                    minimal.setVisitorName(v.getVisitorName());
                    minimal.setVehicleName(v.getVehicleName());
                    minimal.setVehicleRegistrationNumber(v.getVehicleRegistrationNumber());
                    minimal.setVisitPurpose(v.getVisitPurpose());
                    minimal.setVisitorType(v.getVisitorType());
                    minimal.setActiveVisitor(v.isActiveVisitor());
                    minimal.setTimeIn(v.getTimeIn());
                    minimal.setTimeOut(v.getTimeOut());
                    minimal.setPhoneNumber(v.getPhoneNumber());
                    return minimal;
                })
                .collect(Collectors.toList());
    }

}
