package com.parkify.vehitrack.repository;

import com.parkify.vehitrack.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRepository extends JpaRepository<Resident,Integer> {

}
