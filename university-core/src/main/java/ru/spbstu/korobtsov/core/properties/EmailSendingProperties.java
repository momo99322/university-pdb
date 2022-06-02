package ru.spbstu.korobtsov.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "university.email")
public class EmailSendingProperties {

    private String email;

    private String messageForStudent;

    private String messageForLecturer;

    private Integer maxAllowedMissedLectureCount;
}
