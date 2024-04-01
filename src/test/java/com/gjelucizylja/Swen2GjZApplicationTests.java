package com.gjelucizylja;

import com.gjelucizylja.persistence.entities.Person;
import com.gjelucizylja.persistence.repositories.PersonRepository;
import com.gjelucizylja.services.TourLogService;
import com.gjelucizylja.services.TourService;
import com.gjelucizylja.services.dtos.TourDto;
import com.gjelucizylja.services.dtos.TourLogDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class Swen2GjZApplicationTests {


    @Autowired
    private TourService tourService;

    @Test
    void testSaveNewTour() {
        TourDto tourDto = new TourDto();
        tourDto.setName("Test Tour");
        tourDto.setFromLocation("Location A");
        tourDto.setToLocation("Location B");
        tourDto.setTourDistance(50);
        tourDto.setTourDescription("A scenic tour");
        tourDto.setRouteInformationImageUrl("https://example.com/image.jpg");
        tourDto.setEstimatedTime(3.5);
        tourDto.setTransportType("Bus");

        boolean savedTour = tourService.saveNewTour(tourDto);

        assertTrue(savedTour);
    }


    @Test
    void getTourListNotEmpty(){
        final String uri = "http://localhost:8080/tour";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        assertNotEquals(false, result.contains("id"));
    }

    @Test
    void deleteTourSuccessful(){
        final String uri = "http://localhost:8080/tour/1";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(uri, String.class);

        final String uri2 = "http://localhost:8080/tour";

        RestTemplate restTemplate1 = new RestTemplate();
        String result = restTemplate1.getForObject(uri2, String.class);
       assertEquals(false, result.contains("id:1"));
    }

    @Test
    void getTourLogs(){
        final String uri = "http://localhost:8080/tour-logs";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        assertNotEquals(false, result.contains("id"));

    }

    @Test
    void deleteTourLogSuccessful(){
        final String uri = "http://localhost:8080/tour-logs/1";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(uri, String.class);

        final String uri2 = "http://localhost:8080/tour-logs";

        RestTemplate restTemplate1 = new RestTemplate();
        String result = restTemplate1.getForObject(uri2, String.class);
        assertEquals(false, result.contains("id:1"));
    }
}
