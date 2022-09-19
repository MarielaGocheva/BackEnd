package com.example.individual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class IndividualApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndividualApplication.class, args);
    }

}
