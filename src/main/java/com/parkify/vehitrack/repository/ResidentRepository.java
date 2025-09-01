package com.parkify.vehitrack.repository;

import com.parkify.vehitrack.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Integer> {

    @Query("SELECT r FROM Resident r " +
            "WHERE (:firstName IS NULL OR r.fName = :firstName) " +
            "AND (:lastName IS NULL OR r.lName = :lastName)")
    List<Resident> findByFirstAndLastName(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName);
}
