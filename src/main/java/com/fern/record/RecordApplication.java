package com.fern.record;

import com.fern.record.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.Arrays;

@SpringBootApplication
public class RecordApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{RecordApplication.class, AppConfig.class}, args);
    }

}
