package com.fern.record;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@SpringBootTest(
        properties = "application.yml",
        classes = {AppConfig.class, RecordApplication.class}
)
@RunWith(SpringRunner.class)
public class RecordApplicationTests {


    @Autowired
    private AppConfig appConfig;

    protected Random random;

    @BeforeEach
    public void before() {
        System.out.println(appConfig.getFieldA() + " -------> ");
        random = new Random(100);
    }
}
