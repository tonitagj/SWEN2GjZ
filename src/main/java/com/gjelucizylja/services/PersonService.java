package com.gjelucizylja.services;

import com.gjelucizylja.persistence.entities.Person;
import com.gjelucizylja.persistence.repositories.PersonRepository;
import com.gjelucizylja.services.dtos.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    void saveNewPerson(PersonDto personDto);
    List<PersonDto> getAllPersons();
    List<PersonDto> getPersonByName(String name);
}
