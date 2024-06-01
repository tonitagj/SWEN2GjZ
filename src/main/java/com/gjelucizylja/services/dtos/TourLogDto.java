package com.gjelucizylja.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourLogDto {
    private Long id;
    private Long tourId;
    private LocalDate date;
    private String comment;
    private int difficulty;
    private double totalDistance;
    private double totalTime;
    private int rating;
}
