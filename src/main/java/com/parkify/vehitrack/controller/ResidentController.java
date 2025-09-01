package com.parkify.vehitrack.controller;

import com.parkify.vehitrack.entity.Resident;
import com.parkify.vehitrack.service.ResidentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
