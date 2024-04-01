package com.gjelucizylja.services.imp;

import com.gjelucizylja.persistence.entities.Person;
import com.gjelucizylja.persistence.repositories.PersonRepository;
import com.gjelucizylja.services.PersonService;
import com.gjelucizylja.services.dtos.PersonDto;
import com.gjelucizylja.services.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonMapper personMapper;

    @Override
    public void saveNewPerson(PersonDto personDto) {
        Person entity = Person.builder()
                .name(personDto.getName())
                .email(personDto.getEmail())
                .age(personDto.getAge())
                .build();
        personRepository.save(entity);
    }

    @Override
    public List<PersonDto> getAllPersons() {
        return personMapper.mapToDto(personRepository.findAll());
    }

    @Override
    public List<PersonDto> getPersonByName(String name) {
        return personMapper.mapToDto(personRepository.findByNameIgnoreCase(name));
    }

}
