# 开发环境

- IDEA2020.1
- JDK1.8
- SpringBoot2.4.1
- SpringCloud2020.0.0
- Maven-3.6.1

```
Spring Boot
https://docs.spring.io/spring-boot/docs/2.4.1/reference/html/

Spring Cloud
https://docs.spring.io/spring-cloud/docs/2020.0.0/reference/html/
```

# 开发模块

```
<module>netflix-eureka</module>
<module>generator</module>
<module>dynamic-datasource</module>
<module>business</module>
<module>sync</module>
```

# 功能列举

### 定时任务

```java
@EnableAsync//开启异步定时任务
@EnableScheduling//开启定时任务

@Async
@Scheduled(fixedRate = 1 * 1 * 20 * 1000)//每隔20s刷新一下
public void cron() throws IOException {
System.out.println("cron running ..." + System.currentTimeMillis());
}
```

### 自定义Return实体类

```java
@ApiOperation("api")
@GetMapping("/api")
public Result<?> api() throws Exception {
return Result.OK("you have a message!", null);
}
```

### 多数据源

```yml
#多数据源配置
spring:
  application:
    name: business
  datasource:
    dynamic:
      primary: dev #设置默认的数据源或者数据源组,默认值即为master
      strict: false #严格匹配数据源,默认false. true未匹配到指定数据源时抛异常,false使用默认数据源
      datasource:
        dev:
          driver-class-name: com.sap.db.jdbc.Driver
          url: jdbc:sap:10.109.236.152:39041
          username: WIP
          password: xxxxxx
        uat:
          driver-class-name: com.sap.db.jdbc.Driver
          url: jdbc:sap:10.109.236.11:38041
          username: WIP
          password: xxxxxx
        #......省略
        #以上会配置一个默认库master，一个组slave下有两个子库slave_1,slave_2
```

实现跨数据源的注解@DS，下面是具体的实现类

```java
@Service
@DS("dev")
public class MultiServiceImpl implements MultiService {

    @Autowired
    private HanaService hanaService;

    public List<SyncData> selectDev() {
        LambdaQueryWrapper<SyncData> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<SyncData> list = hanaService.list();
        return list;
    }

    @DS("uat")
    @Override
    public boolean saveBatchUat(List<SyncData> list) {
        boolean saveBatch = hanaService.saveBatch(list);
        return saveBatch;
    }
}
```

### 数据库加密

```yml
#多数据源配置
spring:
  application:
    name: business
  datasource:
    dynamic:
      primary: dev #设置默认的数据源或者数据源组,默认值即为master
      strict: false #严格匹配数据源,默认false. true未匹配到指定数据源时抛异常,false使用默认数据源
      datasource:
        dev:
          driver-class-name: com.sap.db.jdbc.Driver
          url: jdbc:sap:10.109.236.152:39041
          username: WIP
          password: ENC(zM5Q0AGHmp9iCoXNLDBhxw==)
        uat:
          driver-class-name: com.sap.db.jdbc.Driver
          url: jdbc:sap:10.109.236.11:38041
          username: WIP
          password: ENC(J5CIrkQxCguoo2kHy516fg==)
        #......省略
        #以上会配置一个默认库master，一个组slave下有两个子库slave_1,slave_2
        
jasypt:
  encryptor:
    algorithm: PBEWithMD5AndDES
    iv-generator-classname: org.jasypt.iv.NoIvGenerator
    #加密秘钥
    password: custom-salt
    #password: ${JASYPT_ENCRYPTOR_PASSWORD:}
```

### i18n国际化支持

```yml
spring:
  application:
    name: business
  #i18n
  messages:
    always-use-message-format: false
    basename: i18n/messages
    cache-duration: 3600
    encoding: UTF-8
    fallback-to-system-locale: false
```

* messages.properties
* messages_en.properties
* messages_en_US.properties
* messages_zh.properties
* messages_zh_CN.properties

# 打包项目

### Lifecycle

- package

# 运行项目

```shell
java -jar xxx.jar
```

