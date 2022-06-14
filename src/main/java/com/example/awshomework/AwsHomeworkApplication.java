package com.example.awshomework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AwsHomeworkApplication {

    public static void main(String[] args) {

        SpringApplication.run(AwsHomeworkApplication.class, args);
    }

}
