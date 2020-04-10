package com.fern.record;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
@Data
public class AppConfig {

    @Value("app.fieldA")
    private String fieldA;
}
