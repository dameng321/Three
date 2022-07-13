package com.dameng.common.core.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 *  跨域配置类
 * </p>
 *
 * @author 大梦
 * @since 2020-5-20
 */
@SpringBootConfiguration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //添加映射路径
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(true)
                //设置放行哪些原始域   SpringBoot2.4.4以上版本使用.allowedOriginPatterns("*")
//                .allowedOrigins("*")
                .allowedOriginPatterns("*")
                //放行哪些请求方式
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS")
                //或者放行全部
//                .allowedMethods("*")
                //放行哪些原始请求头部信息
                .allowedHeaders("*")
                //暴露哪些原始请求头部信息
                .exposedHeaders("*");
    }
}