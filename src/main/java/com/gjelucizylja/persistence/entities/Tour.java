package com.gjelucizylja.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "TOUR")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String tourDescription;
    private String fromLocation;
    private String toLocation;
    private String transportType;
    private double tourDistance;
    private double estimatedTime;
    private String routeInformationImageUrl;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourLog> tourLogs = new ArrayList<>();


}