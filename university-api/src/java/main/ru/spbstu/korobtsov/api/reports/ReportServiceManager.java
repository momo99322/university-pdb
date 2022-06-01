package ru.spbstu.korobtsov.api.reports;

import org.springframework.util.MimeType;

import java.util.Collection;

public interface ReportServiceManager {

    ReportService getReportService(MimeType type);

    Collection<MimeType> getSuppoertedFormats();
}
