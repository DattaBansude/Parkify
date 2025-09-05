package com.parkify.vehitrack.controller;

import com.parkify.vehitrack.entity.Visitors;
import com.parkify.vehitrack.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
