package ru.spbstu.korobtsov.core;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EnableConfigurationProperties
@EntityScan(basePackages = "ru.spbstu.korobtsov.api.domain")
@ComponentScan(basePackageClasses = CoreAutoConfiguration.class)
public class CoreAutoConfiguration {


    @Bean
    @Primary
    @ConfigurationProperties(prefix = "university.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }
}

