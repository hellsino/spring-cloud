package com.domain.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 配置分页插件
 */
//Spring boot方式
@EnableTransactionManagement
@MapperScan("com.domain.mapper")
@Configuration
public class MybatisPlusConfig {

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 方式一:
        //interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        // 方式二:
        PaginationInnerInterceptor page = new PaginationInnerInterceptor();
        page.setDbType(DbType.MYSQL);
        // 设置最大单页限制数量,默认 20 条,-1 不受限制
        page.setMaxLimit(Long.parseLong("-1")); // 设置每页 50 条, page.setMaxLimit(Long.parseLong("50"));
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        page.setOverflow(false);

        interceptor.addInnerInterceptor(page);
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }

    //TODO ----------------------------------------------版本分割线-------------------------------------------------------

    /**
     * 旧版分页插件配置方法（Mybatis Plus 3.4.0版本之前）  mybatis-plus-boot-starter 3.3.2
     */
    //@Bean
    //public PaginationInterceptor paginationInterceptor() {
    //    PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
    //    // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
    //    // paginationInterceptor.setOverflow(false);
    //    // 设置最大单页限制数量，默认 500 条，-1 不受限制
    //    paginationInterceptor.setLimit(-1);
    //    // 开启 count 的 join 优化,只针对部分 left join
    //    paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
    //    return paginationInterceptor;
    //}
}
