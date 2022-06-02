package ru.spbstu.korobtsov.core;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.MimeType;
import ru.spbstu.korobtsov.api.reports.ReportService;

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
@ConfigurationPropertiesScan(basePackageClasses = CoreAutoConfiguration.class)
public class CoreAutoConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "university.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public XmlMapper xmlMapper(Jackson2ObjectMapperBuilder builder) {
        return builder.createXmlMapper(true).build();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer objectMapperBuilderCustomizer() {
        return builder -> builder.modulesToInstall(new JavaTimeModule());
    }

    @Bean
    Map<MimeType, ReportService> reportServiceMap(List<ReportService> reportServices) {
        return reportServices.stream().collect(toMap(ReportService::getType, Function.identity()));
    }
}

