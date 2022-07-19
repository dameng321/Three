package com.dameng.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2022-6-22 14:13
 **/

public class GeneratorCode {

    @Test
    public void code(){
        String url = "jdbc:mysql://1.116.45.32:3366/three?characterEncoding=utf8&serverTimezone=UTC";
        String username = "root";
        String password = "dameng";

        List<String> tables = new ArrayList<>();
        tables.add("user");
        tables.add("user_role");
        tables.add("role");
        tables.add("role_permission");
        tables.add("permission");


        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("dameng")               //作者
                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java")    //输出路径(写到java目录)
                            .enableSwagger()           //开启swagger
                            .commentDate("yyyy-MM-dd")
                            .disableOpenDir()

                    ;

                })
                .packageConfig(builder -> {
                    builder.parent("com.dameng")
                            .moduleName("system")
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.Impl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "\\src\\main\\resources\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables)
                            //.addTablePrefix("p_")
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            .enableLombok()
                            .idType(IdType.ASSIGN_ID)
                            .logicDeleteColumnName("is_deleted")
                            .versionColumnName("version")
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            .formatFileName("%sController")
                            .enableRestStyle()
                            .mapperBuilder()
                            .enableBaseResultMap()  //生成通用的resultMap
                            .enableBaseColumnList()
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()
                            .formatXmlFileName("%sMapper");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }

}
