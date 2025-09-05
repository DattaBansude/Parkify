package com.parkify.vehitrack.repository;

import com.parkify.vehitrack.entity.Visitors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitorRepository extends JpaRepository<Visitors, Integer> {

    List<Visitors> findByVehicalRegisterationNumber(String vehicalRegisterationNumber);

}
