package ru.spbstu.korobtsov.api;

import org.springframework.util.MimeType;

public interface ReportService {

    String generateReportByLectureName(String name);

    String generateReportByStudentName(String name);

    MimeType getType();
}
