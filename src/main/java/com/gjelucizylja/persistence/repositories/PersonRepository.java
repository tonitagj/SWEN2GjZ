package com.gjelucizylja.persistence.repositories;

import com.gjelucizylja.persistence.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByNameIgnoreCase(String name);
}
