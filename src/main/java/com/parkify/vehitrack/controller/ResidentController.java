package com.parkify.vehitrack.controller;

import com.parkify.vehitrack.entity.Resident;
import com.parkify.vehitrack.service.ResidentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residents")
@Tag(name = "Resident API", description = "Manage residents of the society")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @PostMapping("/addResident")
    @Operation(
            summary = "Add a new resident",
            description = "Creates a new resident with basic details",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Resident created successfully"),
                    @ApiResponse(responseCode = "400", description = "Validation failed")
            }
    )
    public ResponseEntity<Resident> createResident(@Valid @RequestBody Resident resident) {
        Resident saved = residentService.createResident(resident);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);

    }

    @GetMapping("/getResidents")
    @Operation(summary = "Get all residents with their vehicles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved residents"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Resident>> getAllResidents() {
        List<Resident> residents = residentService.getAllResidents();
        return new ResponseEntity<>(residents, HttpStatus.OK);
    }

    @GetMapping("/searchByName")
    @Operation(
            summary = "Search residents by name",
            description = "Search residents using optional first name and/or last name."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of matching residents returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Resident>> getResidentByName(@RequestParam(required = false) String firstName,@RequestParam(required = false) String lastName)
    {
        List<Resident> residents = residentService.getResidentByName(firstName, lastName);
        return new ResponseEntity<>(residents, HttpStatus.OK);
    }

}
