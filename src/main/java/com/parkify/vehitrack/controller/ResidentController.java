package com.parkify.vehitrack.controller;

import com.parkify.vehitrack.entity.Resident;
import com.parkify.vehitrack.service.ResidentService;
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
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @PostMapping("/addResident")
    public ResponseEntity<Resident> createResident(@Valid @RequestBody Resident resident) {
        Resident saved = residentService.createResident(resident);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);

    }
}
