package com.gjelucizylja.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "tour_logs")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "comment")
    private String comment;

    @Column(name = "difficulty")
    private int difficulty;

    @Column(name = "total_distance")
    private double totalDistance;

    @Column(name = "total_time")
    private double totalTime;

    @Column(name = "rating")
    private int rating;

}
