package com.gjelucizylja.persistence.repositories;

import com.gjelucizylja.persistence.entities.Person;
import com.gjelucizylja.persistence.entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long>{
    List<Tour> findTourByNameIgnoreCase(String name);
}
