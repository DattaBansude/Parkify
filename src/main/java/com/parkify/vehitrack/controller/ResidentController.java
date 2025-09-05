package com.parkify.vehitrack.controller;

import com.parkify.vehitrack.entity.Resident;
import com.parkify.vehitrack.service.ResidentService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ResponseEntity<Resident> createResident(@RequestBody Resident resident) {
        Resident saved = residentService.createResident(resident);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);

    }

    @GetMapping("/getResidents")
    public ResponseEntity<List<Resident>> getAllResidents() {
        List<Resident> residents = residentService.getAllResidents();
        return new ResponseEntity<>(residents, HttpStatus.OK);
    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<Resident>> getResidentByName(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        List<Resident> residents = residentService.getResidentByName(firstName, lastName);
        return new ResponseEntity<>(residents, HttpStatus.OK);
    }

}
