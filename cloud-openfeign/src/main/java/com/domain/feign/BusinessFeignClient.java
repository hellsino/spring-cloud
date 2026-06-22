package com.domain.feign;

import com.domain.config.OpenFeignConfiguration;
import com.domain.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @FeignClient中的value是要调用的服务的注册名，即OpenFeignServer在eureka的注册名，application.yml
 * FeignClient的Request路径，方式和参数要和被调用的Rest服务保持一致
 */
@FeignClient(value = "business", configuration = OpenFeignConfiguration.class)
@Service
public interface BusinessFeignClient {

    @GetMapping("/user/feign")
    ResponseEntity<List<User>> feign();

}
