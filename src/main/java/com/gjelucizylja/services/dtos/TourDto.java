package com.gjelucizylja.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourDto {
    private Long id;
    private String name;
    private String tourDescription;
    private String fromLocation;
    private String toLocation;
    private String transportType;
    private double tourDistance;
    private double estimatedTime;
    private String routeInformationImageUrl;
}