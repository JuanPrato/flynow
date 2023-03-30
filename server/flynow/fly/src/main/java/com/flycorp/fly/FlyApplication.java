package com.flycorp.fly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FlyApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlyApplication.class, args);
    }
}