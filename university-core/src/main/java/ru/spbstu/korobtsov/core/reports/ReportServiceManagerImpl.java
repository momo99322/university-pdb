package ru.spbstu.korobtsov.core.reports;

import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;
import ru.spbstu.korobtsov.api.reports.ReportService;
import ru.spbstu.korobtsov.api.reports.ReportServiceManager;

import java.util.Collection;
import java.util.Map;

@Component
public class ReportServiceManagerImpl implements ReportServiceManager {

    private final Map<MimeType, ReportService> reportServiceMap;

    public ReportServiceManagerImpl(Map<MimeType, ReportService> reportServiceMap) {
        this.reportServiceMap = reportServiceMap;
    }


    @Override
    public ReportService getReportService(MimeType type) {
        return reportServiceMap.get(type);
    }

    @Override
    public Collection<MimeType> getSuppoertedFormats() {
        return reportServiceMap.keySet();
    }
}
