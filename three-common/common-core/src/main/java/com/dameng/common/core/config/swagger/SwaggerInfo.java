package com.dameng.common.core.config.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Swagger配置类参数
 * </p>
 *
 * @author 大梦
 * @since 2020-5-20
 */

@Data
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerInfo {

    /**
     * 标题
     */
    private String title;

    /**
     * 简介
     */
    private String description;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 版本
     */
    private String version;

    /**
     * 扫描的包路径
     */
    private String basePackage;

    /**
     * 接口作者
     */
    private String author;

}
