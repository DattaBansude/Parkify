package com.parkify.vehitrack.controller;

import com.parkify.vehitrack.dto.VisitorDTO;
import com.parkify.vehitrack.entity.VisitorType;
import com.parkify.vehitrack.entity.Visitors;
import com.parkify.vehitrack.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitors")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @PostMapping("/addVisitor")
    public ResponseEntity<Visitors> createVisitor(@RequestBody Visitors visitors) {
        Visitors visitor = visitorService.addVisitor(visitors);
        return new ResponseEntity<>(visitor, HttpStatus.OK);
    }


    @GetMapping("/getByRegistrationNumber")
    public ResponseEntity<VisitorDTO> getVisitorByRegistrationNumber(
            @RequestParam String registrationNumber) {
        VisitorDTO visitorDTO = visitorService.getVisitorByRegistrationNumber(registrationNumber);
        return new ResponseEntity<>(visitorDTO, HttpStatus.OK);
    }

    @PatchMapping("/exit/{registrationNumber}")
    public ResponseEntity<Visitors> updateVisitorExitTime(@PathVariable String registrationNumber) {

        Visitors updateVisitor = visitorService.updateVisitorExitTime(registrationNumber);
        return new ResponseEntity<>(updateVisitor, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Visitors>> getActiveVisitors(
            @RequestParam(required = false) List<VisitorType> visitorType) {

        List<Visitors> visitors = visitorService.getActiveVisitors(visitorType);
        return new ResponseEntity<>(visitors, HttpStatus.OK);

    }

    // Manual trigger for testing
  /*  @PostMapping("/createBackup")
    public ResponseEntity<String> createBackupNow() {
        visitorService.createDailyVisitorBackup();
        return ResponseEntity.ok(" Visitor backup created successfully!");
    }*/
}
