package com.domain;

import com.domain.entity.RandomData;
import com.domain.mapper.UserMapper;
import lombok.SneakyThrows;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest(classes = BusinessApplication.class)
public class BusinessApplicationTests {

    @Qualifier(value = "asyncServiceExecutor")
    @Autowired
    private Executor executor;

    @Autowired
    private RandomData randomData;

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        byte[] buffer = new byte[1024 * 1024 * 1024]; // 1GB
    }

    @Test
    void selectLinkedHashMap() {
        List<LinkedHashMap> linkedHashMap = userMapper.selectLinkedHashMap();
        System.out.println(linkedHashMap.get(0).get("id"));
        System.out.println(linkedHashMap.get(0).get("username"));
        System.out.println(linkedHashMap.get(0));
        System.err.println("-----------------------------------------------------------------------------------------");
        linkedHashMap.forEach(System.out::println);
    }

    @Test
    void randomData() {
        System.out.println(randomData);
    }

    @Test
    void thread() {
        //多线程初始化参数
        int thread = 100;
        CountDownLatch latch = new CountDownLatch(thread);
        for (int i = 0; i < 100; i++) {
            ConcurrentHashMap<String, Object> data = new ConcurrentHashMap<>();
            data.put("SHOP_ORDER", i);
            //执行多线程逻辑
            executor.execute(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    try {
                        long l = System.currentTimeMillis();
                        System.out.println("service logic execute...");
                        threadRun(data);
                        System.out.println("service logic success..." + (System.currentTimeMillis() - l));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            });
        }
    }

    private void threadRun(ConcurrentHashMap<String, Object> data) {
        System.out.println("data = " + data.get("SHOP_ORDER"));
    }

    /**
     * 加密
     */
    @Test
    void encode() {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();

        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword("custom-salt");
        standardPBEStringEncryptor.setConfig(config);
        String plainText = "123456";
        String encryptedText = standardPBEStringEncryptor.encrypt(plainText);
        System.out.println(encryptedText);
    }

    /**
     * 解密
     */
    @Test
    void decode() {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();

        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword("custom-salt");
        standardPBEStringEncryptor.setConfig(config);
        String encryptedText = "uDDUhTu/qgcBFjWnF0BE7A==";
        String plainText = standardPBEStringEncryptor.decrypt(encryptedText);
        System.out.println(plainText);
    }
}
