package com.dameng.common.core.config.swagger;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 *  Swagger配置类
 * </p>
 *
 * @author 大梦
 * @since 2020-5-20
 */
@Configuration
@EnableSwagger2
@AllArgsConstructor
@Profile({"dev", "test"})
@EnableConfigurationProperties(SwaggerInfo.class)
public class SwaggerConfiguration {

    @Autowired
    private SwaggerInfo swaggerInfo;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(swaggerInfo.getGroupName())
                .enableUrlTemplating(false)
                .forCodeGeneration(true)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerInfo.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 设置api信息
     *
     * @return 返回ApiInfo
     */
    private ApiInfo apiInfo() {
        StringVendorExtension vendorExtension = new StringVendorExtension("Three", "");
        Collection<VendorExtension> vendorExtensions = new ArrayList<>();
        vendorExtensions.add(vendorExtension);
        Contact contact = new Contact("大梦", "", "dameng6@qq.com");
        return new ApiInfo(
                swaggerInfo.getTitle(),
                swaggerInfo.getDescription(),
                swaggerInfo.getVersion(),
                "", contact, "", "",
                vendorExtensions);
    }

}
