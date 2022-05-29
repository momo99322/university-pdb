package ru.spbstu.korobtsov.core;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.MimeType;
import ru.spbstu.korobtsov.api.ReportService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Configuration
@EnableJpaRepositories
@EnableConfigurationProperties
@EntityScan(basePackages = "ru.spbstu.korobtsov.api.domain")
@PropertySources(
        {
                @PropertySource("config/application.yaml"),
                @PropertySource("application.yaml")
        }
)
@ComponentScan(basePackageClasses = CoreAutoConfiguration.class)
public class CoreAutoConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "university.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public XmlMapper xmlMapper() {
        return new XmlMapper();
    }

    @Bean
    public Map<MimeType, ReportService> reportServiceMap(List<ReportService> reportServices) {
        return reportServices.stream().collect(toMap(ReportService::getType, Function.identity()));
    }
}

