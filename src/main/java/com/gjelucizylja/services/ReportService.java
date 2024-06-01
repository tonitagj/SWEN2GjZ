package com.gjelucizylja.services;

import com.gjelucizylja.persistence.entities.Tour;
import com.gjelucizylja.persistence.entities.TourLog;
import com.gjelucizylja.persistence.repositories.TourLogRepository;
import com.gjelucizylja.persistence.repositories.TourRepository;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import jakarta.servlet.ServletOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReportService {
    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourLogRepository tourLogRepository;

    public byte[] generateTourReport(Long tourId) {
        log.info("Generating tour report for tour id: {}", tourId);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new IllegalArgumentException("Tour not found"));
        List<TourLog> tourLogs = tourLogRepository.findByTourId(tourId);

        log.info("Adding tour details to report for tour id: {}", tourId);
        document.add(new Paragraph("Tour Report"));
        document.add(new Paragraph("Name: " + tour.getName()));
        document.add(new Paragraph("Description: " + tour.getTourDescription()));
        document.add(new Paragraph("From: " + tour.getFromLocation()));
        document.add(new Paragraph("To: " + tour.getToLocation()));
        document.add(new Paragraph("Transport Type: " + tour.getTransportType()));
        document.add(new Paragraph("Distance: " + tour.getTourDistance()));
        document.add(new Paragraph("Estimated Time: " + tour.getEstimatedTime()));

        log.info("Adding tour logs to report for tour id: {}", tourId);
        document.add(new Paragraph("Tour Logs:"));
        Table table = new Table(UnitValue.createPercentArray(new float[]{1, 2, 2, 1, 1, 1}));
        table.addHeaderCell("ID");
        table.addHeaderCell("Date");
        table.addHeaderCell("Comment");
        table.addHeaderCell("Difficulty");
        table.addHeaderCell("Distance");
        table.addHeaderCell("Time");
        for (TourLog log : tourLogs) {
            table.addCell(String.valueOf(log.getId()));
            table.addCell(String.valueOf(log.getDate()));
            table.addCell(log.getComment());
            table.addCell(String.valueOf(log.getDifficulty()));
            table.addCell(String.valueOf(log.getTotalDistance()));
            table.addCell(String.valueOf(log.getTotalTime()));
        }
        document.add(table);
        document.close();

        log.info("Tour report generated successfully for tour id: {}", tourId);
        return baos.toByteArray();
    }

    public byte[] generateSummaryReport() {
        log.info("Generating summary report");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Summary Report"));
        List<Tour> tours = tourRepository.findAll();
        Table table = new Table(UnitValue.createPercentArray(new float[]{2, 1, 1, 1}));
        table.addHeaderCell("Tour Name");
        table.addHeaderCell("Average Time");
        table.addHeaderCell("Average Distance");
        table.addHeaderCell("Average Rating");

        for (Tour tour : tours) {
            log.info("Calculating averages for tour id: {}", tour.getId());
            List<TourLog> tourLogs = tourLogRepository.findByTourId(tour.getId());

            double averageTime = tourLogs.stream().mapToDouble(TourLog::getTotalTime).average().orElse(0);
            double averageDistance = tourLogs.stream().mapToDouble(TourLog::getTotalDistance).average().orElse(0);
            double averageRating = tourLogs.stream().mapToDouble(TourLog::getRating).average().orElse(0);

            table.addCell(tour.getName());
            table.addCell(String.valueOf(averageTime));
            table.addCell(String.valueOf(averageDistance));
            table.addCell(String.valueOf(averageRating));
        }
        document.add(table);
        document.close();

        log.info("Summary report generated successfully");
        return baos.toByteArray();
    }
}
