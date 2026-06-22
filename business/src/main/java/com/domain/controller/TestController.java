package com.domain.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hellsino
 * @since 2020-01-01 00:00:00.000
 */
@Api(tags = "test")
@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation("example")
    @GetMapping("/example")
    public ResponseEntity example() {
        String url = "http://example.com";
        String template = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok().body(template);
    }
}
