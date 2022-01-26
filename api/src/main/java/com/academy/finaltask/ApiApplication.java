package com.academy.finaltask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;


@SpringBootApplication
@EnableRetry
public class ApiApplication {

    @Retryable(value = {RuntimeException.class})
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
