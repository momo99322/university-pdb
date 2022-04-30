package ru.spbstu.korobtsov.core;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EntityScan(basePackages = "ru.spbstu.korobtsov.api.domain")
@ComponentScan(basePackageClasses = CoreAutoConfiguration.class)
public class CoreAutoConfiguration {
}

