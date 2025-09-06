package com.parkify.vehitrack.repository;

import com.parkify.vehitrack.entity.VisitorType;
import com.parkify.vehitrack.entity.Visitors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitors, Integer> {

    // Find all visitors with a given registration number
    @Query("SELECT v FROM Visitors v WHERE v.vehicleRegistrationNumber = :registrationNumber")
    List<Visitors> findByVehicleRegistrationNumber(@Param("registrationNumber") String registrationNumber);

    // Find the latest visitor record by registration number
    @Query("SELECT v FROM Visitors v WHERE v.vehicleRegistrationNumber = :registrationNumber ORDER BY v.timeIn DESC")
    Optional<Visitors> findTopByVehicleRegistrationNumberOrderByTimeInDesc(@Param("registrationNumber") String registrationNumber);

    // Active visitors only
    @Query("SELECT v FROM Visitors v WHERE v.isActiveVisitor = true")
    List<Visitors> getActiveVisitors();

    // Active visitors filtered by types
    @Query("SELECT v FROM Visitors v WHERE v.isActiveVisitor = true AND v.visitorType IN :types")
    List<Visitors> getActiveVisitorsByTypes(@Param("types") List<VisitorType> visitorTypes);

    @Query("SELECT v FROM Visitors v WHERE v.timeIn BETWEEN :start AND :end")
    List<Visitors> findByTimeInBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
