package com.parkify.vehitrack.helper;

import com.parkify.vehitrack.entity.Vehicle;

import java.time.LocalDateTime;

public class VehicleHelper {
    // Set default values when creating a vehicle
    public static void initializeVehicle(Vehicle vehicle) {
        vehicle.setAssociationActivatedAt(LocalDateTime.now());
        vehicle.setVehicleActive(true);
    }
}
