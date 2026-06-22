package com.domain;

import com.domain.config.CustomLoadBalancerConfiguration;
import com.domain.config.OpenFeignConfiguration;
import com.domain.feign.BusinessFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

// 开启远程调用
@EnableFeignClients(clients = BusinessFeignClient.class, defaultConfiguration = OpenFeignConfiguration.class)

// 开启负载均衡
@LoadBalancerClients(defaultConfiguration = {CustomLoadBalancerConfiguration.class})
//@LoadBalancerClients({@LoadBalancerClient(value = "customers", configuration = CustomLoadBalancerConfiguration.class)})

@SpringBootApplication
public class CloudOpenFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudOpenFeignApplication.class, args);
    }

    @LoadBalanced // 负载均衡
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
