package com.gjelucizylja.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourLogDto {
    private Long id;
    private Long tourId;
    private LocalDateTime dateTime;
    private String comment;
    private int difficulty;
    private double totalDistance;
    private double totalTime;
    private int rating;
}
