package com.gjelucizylja;

import com.gjelucizylja.persistence.entities.Person;
import com.gjelucizylja.persistence.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Swen2GjZApplication {

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(Swen2GjZApplication.class, args);
    }



    void initFakeData() {
        Person p = Person.builder()
                .name("Markus")
                .email("holzerm@technikum-wien.at")
                .age(32)
                .build();

        personRepository.save(p);

        personRepository.save(Person.builder()
                .name("Anna")
                .age(33)
                .email("anna@technikum-wien.at")
                .build());

        System.out.printf("%d rows in table person\n", personRepository.count());
        personRepository.findAll().forEach(System.out::println);
    }
}
