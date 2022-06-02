package ru.spbstu.korobtsov.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "university.sms")
public class SmsSendingProperties {

    private String phone;

    private String message;

    private Double allowableAvgGrade;
}
