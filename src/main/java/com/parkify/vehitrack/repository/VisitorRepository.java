package com.parkify.vehitrack.repository;

import com.parkify.vehitrack.entity.Visitors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends JpaRepository<Visitors, Integer> {

}
