package com.gjelucizylja;

import com.gjelucizylja.persistence.entities.Person;
import com.gjelucizylja.persistence.repositories.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Swen2GjZApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private PersonRepository personRepository;

    @Test
    void test_Person(){
        Person person = Person.builder()
                .name("Tonita")
                .email("tonitagjeluci@gmail.com")
                .age(23)
                .build();
        personRepository.save(Person.builder()
                .name("Livia")
                .email("liviazylia@gmail.com")
                .age(21)
                .build());
        System.out.printf("%d rows in table person \n", personRepository.count());
        personRepository.findAll().forEach(System.out::println);
    }
}
