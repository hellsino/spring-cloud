package com.domain.controller;

import com.domain.entity.User;
import com.domain.feign.BusinessFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("cloud-openfeign")
public class CloudOpenFeignController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private BusinessFeignClient businessFeignClient;

    /**
     * http://localhost:8088/cloud-openfeign/feign
     */
    @GetMapping("/feign")
    public ResponseEntity<List<User>> feign() {
        return businessFeignClient.feign();
    }

    /**
     * 获取eureka实例信息
     */
    @GetMapping("/serviceInstanceInfo")
    public void serviceInstanceInfo() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("cloud-openfeign");
        log.info("serviceId-->{},Host-->{},port-->{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
    }

    /**
     * http://localhost:8088/cloud-openfeign/instanceInvoke
     */
    @GetMapping("/instanceInvoke")
    public String instanceInvoke() {
        //1.指定服务,通过LoadBalancerClient自动获取服务中某一个实例
        ServiceInstance instance = loadBalancerClient.choose("BUSINESS");
        //2.获取实例信息,得到指定请求地址
        String host = instance.getHost();
        int port = instance.getPort();
        String url = String.format("http://%s:%s/user/list", host, port);
        log.info(url);
        //3.通过RestTemplate请求远程服务地址
        RestTemplate restTemplate = new RestTemplate();//初始化RestTemplate类
        String template = restTemplate.getForObject(url, String.class);//访问远程地址，并接受返回值
        return template;
    }
}
