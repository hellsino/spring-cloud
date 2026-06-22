package com.domain.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * https://docs.spring.io/spring-cloud-commons/docs/3.0.0/reference/html
 * <p>
 * Spring Cloud 2020.0.x版本移除 Ribbon 后如何修改负载均衡策略
 * 从Spring Cloud 2020.0.0-M1 开始，Spring Cloud 废除了这种英国伦敦地铁站的命名方式，而使用了全新的 "日历化" 版本命名方式
 * 早在2018年，Spring Cloud 在其 Roadmap 里就宣布将要终结的一些库/版本，其中最重要的就是指 Spring Cloud Netflix 项目进入维护模式，然后计划在2020年完全移除
 * Spring Cloud 做出这样的决定其实也是不见得是主动的。我们知道 Spring Cloud 一直以来把 Netflix OSS 套件作为其官方默认的一站式解决方案，那时的 Netflix OSS 套件恨不得可以跟 Spring Cloud 划等号
 * 而Netflix公司在2018年前后宣布其核心组件 Hystrix、Ribbon、Zuul、Archaius 等均进入维护状态
 * 时至今日，Spring Cloud 2020.0 正式发布，在这个主要版本里，按既定计划终于对 spring-cloud-netflix 动刀了
 * 对于Netflix，只保留了其eureka，其他组件全部移除，并给出了推荐替代品
 * Spring Cloud 加入了一个新模块 spring-cloud-loadbalancer 来替代 Ribbon
 * spring-cloud-loadbalancer 有两种负载均衡模式（轮询和随机），默认是用随机轮询
 * 假如想使用随机或者自定义负载均衡策略，就不能按照以前使用Ribbon的模式（注入IRule类，必须引入ribbon依赖）
 * 这里采用 spring-cloud-loadbalancer 的方式配置负载均衡策略
 */
@Configuration
public class CustomLoadBalancerConfiguration {

    @Bean
    ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment, LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new RandomLoadBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
    }
}