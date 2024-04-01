package com.gjelucizylja.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PERSON")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @Column(name = "E_MAIL")
    private String email;
    private int age;
}