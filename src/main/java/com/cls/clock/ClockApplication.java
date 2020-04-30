package com.cls.clock;

import com.cls.clock.dao.ClockTaskDao;
import com.cls.clock.model.ClockTask;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClockApplication{

    public static void main(String[] args) {
        SpringApplication.run(ClockApplication.class, args);
    }

}
