package com.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@EnableAsync // 开启异步定时任务
@EnableScheduling // 开启定时任务
@SpringBootApplication
public class BusinessApplication {

    public static void main(String[] args) {
        //System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(BusinessApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
