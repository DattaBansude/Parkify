package com.parkify.vehitrack.repository;

import com.parkify.vehitrack.entity.Resident;
import com.parkify.vehitrack.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    boolean existsByRegistrationNumber(String registrationNumber);


//    @Query("SELECT v.resident FROM Vehicle v WHERE v.registrationNumber = :registrationNumber")
//    Optional<Resident> findResidentByRegistrationNumber(@Param("registrationNumber") String registrationNumber);

    Optional<Vehicle> findByRegistrationNumber(String registrationNumber);
}
