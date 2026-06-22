package com.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * entity注解，需要数据库表有对应注释
 * 代码生成器无法生成mapper层@Repository注解
 */
public class CodeGenerator {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/ue5?serverTimezone=Asia/Shanghai", "root", "123456")
                .globalConfig(builder -> {
                    // 覆盖已生成文件
                    builder.fileOverride()
                            // 禁止打开输出目录
                            .disableOpenDir()
                            // 指定输出目录
                            .outputDir(System.getProperty("user.dir") + "/business/src/main/java")
                            // 设置作者
                            .author("hellsino")
                            // 开启 swagger 模式
                            .enableSwagger()
                            .enableSwagger()
                            // 注释日期
                            .commentDate("yyyy-MM-dd HH:mm:ss.SSS");
                })
                .packageConfig(builder -> {
                    // 设置父包名
                    builder.parent("com.domain")
                            // 设置父包模块名
                            //.moduleName("xxx")
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapper")
                            .controller("controller")
                            // 设置mapperXml生成路径
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/business/src/main/resources/mapper"));
                })
                .strategyConfig(builder -> {
                    // TODO 输入表名，生成代码
                    // 设置需要生成的表名
                    builder.addInclude("user")
                            // 设置过滤表前缀
                            .addTablePrefix("t_", "c_")
                            // 实体类策略配置
                            .entityBuilder()
                            // 开启lombok
                            .enableLombok()
                            // 说明逻辑删除是哪个字段
                            .logicDeleteColumnName("deleted")
                            // 属性加上说明注解
                            .enableTableFieldAnnotation()
                            // 数据库表字段映射到实体的命名策略
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .idType(IdType.AUTO)

                            // controller策略配置
                            .controllerBuilder()
                            // 开启RestController
                            .enableRestStyle()
                            .formatFileName("%sController")

                            //service策略配置
                            .serviceBuilder()
                            // service类名,%s适配,根据表名替换
                            .formatServiceFileName("%sService")
                            // 同上
                            .formatServiceImplFileName("%sServiceImpl")

                            // mapper策略配置
                            .mapperBuilder()
                            // 设置父类
                            .superClass(BaseMapper.class)
                            // @mapper开启
                            .enableMapperAnnotation()
                            .formatMapperFileName("%sMapper")
                            // XML名
                            .formatXmlFileName("%sMapper");
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
