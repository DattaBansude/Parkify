package com.parkify.vehitrack.repository;

import com.parkify.vehitrack.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Integer> {

    @Query(value = "SELECT * FROM resident r " +
            "WHERE (:firstName IS NULL OR LOWER(r.f_name) LIKE LOWER(CONCAT('%', :firstName, '%'))) " +
            "AND (:lastName IS NULL OR LOWER(r.l_name) LIKE LOWER(CONCAT('%', :lastName, '%')))",
            nativeQuery = true)
    List<Resident> searchByName(@Param("firstName") String firstName,
                                @Param("lastName") String lastName);

}
