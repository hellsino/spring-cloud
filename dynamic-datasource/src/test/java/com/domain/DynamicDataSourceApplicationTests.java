package com.domain;

import com.domain.service.MultiServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = DynamicDataSourceApplication.class)
class DynamicDataSourceApplicationTests {

    @Autowired
    private MultiServiceImpl multiService;

    @Test
    void MultiServiceImpl() {
        List data1 = multiService.selectMySQL();
        data1.forEach(System.out::println);
        System.out.println("-------------");
        List data2 = multiService.selectDev();
        data2.forEach(System.out::println);
    }

}
