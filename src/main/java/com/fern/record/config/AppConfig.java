package com.fern.record.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebFlux
@EnableReactiveMongoRepositories(basePackages = "com.fern.record.repository")
public class AppConfig {

    @Autowired
    private ReactiveGridFsTemplate reactiveGridFsTemplate;


    @PostConstruct
    public void init() {

    }

}
