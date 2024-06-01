package com.gjelucizylja.api;

import com.gjelucizylja.services.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/reports")
@Slf4j
public class ReportApi {

    @Autowired
    private ReportService reportService;

    @GetMapping("/tour/{tourId}")
    public ResponseEntity<byte[]> generateTourReport(@PathVariable Long tourId) {
        log.info("Received request to generate tour report for tourId: {}", tourId);
        byte[] pdf = reportService.generateTourReport(tourId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "inline; filename=tour-report.pdf");
        log.info("Tour report generated successfully for tourId: {}", tourId);
        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }
    @GetMapping("/summary")
    public ResponseEntity<byte[]> generateSummaryReport() {
        log.info("Received request to generate summary report");
        byte[] pdf = reportService.generateSummaryReport();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "inline; filename=summary-report.pdf");
        log.info("Summary report generated successfully");
        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }
}