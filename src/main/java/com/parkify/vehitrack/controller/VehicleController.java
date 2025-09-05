package com.parkify.vehitrack.controller;

import com.parkify.vehitrack.dto.ResidentDTO;
import com.parkify.vehitrack.entity.Vehicle;
import com.parkify.vehitrack.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/addVehicle")
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = vehicleService.createVehicle(vehicle);
        return new ResponseEntity<>(savedVehicle, HttpStatus.OK);
    }

    @GetMapping("/getResidentByRegNo")
    public ResponseEntity<ResidentDTO> getResidentByRegistrationNumber(@RequestParam String registrationNumber) {
        ResidentDTO residentDTO = vehicleService.getResidentByRegistrationNumber(registrationNumber);
        return new ResponseEntity<>(residentDTO, HttpStatus.OK);
    }
}
