package com.parkify.vehitrack.controller;

import com.parkify.vehitrack.dto.ResidentDTO;
import com.parkify.vehitrack.entity.Resident;
import com.parkify.vehitrack.entity.Vehicle;
import com.parkify.vehitrack.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

   /* @PostMapping("/addVehicle")
    public ResponseEntity<?> createVehicle(@RequestBody Vehicle vehicle) {
        try {
            Vehicle savedVehicle = vehicleService.createVehicle(vehicle);
            return ResponseEntity.ok(savedVehicle);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }*/
   @PostMapping("/addVehicle")
   @Operation(summary = "Add a new vehicle", description = "Creates a vehicle with resident association")
   @ApiResponses({
           @ApiResponse(responseCode = "200", description = "Vehicle created successfully"),
           @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input or business rule violation"),
           @ApiResponse(responseCode = "404", description = "Resident not found"),
   })
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
