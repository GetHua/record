package com.fern.record.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
@EnableSpringDataWebSupport
@EnableReactiveMongoRepositories(basePackages = "com.fern.record.repository")
public class AppConfig {


}
