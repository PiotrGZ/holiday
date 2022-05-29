package com.pgz.holiday.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.pgz")
public class HolidayApplication {

    public static void main(String[] args) {
        SpringApplication.run(HolidayApplication.class, args);
    }
}
