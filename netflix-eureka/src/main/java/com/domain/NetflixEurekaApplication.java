package com.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// http://localhost:8761/
@EnableEurekaServer
@SpringBootApplication
public class NetflixEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetflixEurekaApplication.class, args);
    }

}
