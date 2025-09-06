package com.parkify.vehitrack.repository;

import com.parkify.vehitrack.entity.Visitors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitors, Integer> {

    List<Visitors> findByVehicalRegisterationNumber(String vehicalRegisterationNumber);

   // Optional<Visitors> findTopByVehicleRegistrationNumberOrderByTimeInDesc(String vehicleRegistrationNumber);

    Optional<Visitors> findTopByVehicalRegisterationNumberOrderByTimeInDesc(String registrationNumber);
}
