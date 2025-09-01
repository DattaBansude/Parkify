package com.parkify.vehitrack.service;

import com.parkify.vehitrack.entity.Resident;

import java.util.List;

public interface ResidentService {

    Resident createResident(Resident resident);

    List<Resident> getAllResidents();

    List<Resident> getResidentByName(String firstName, String lastName);
}
