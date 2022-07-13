package com.dameng.common.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>Description: SpringSecurity配置排除请求类</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2021/5/30 17:18
 **/
@Data
@Component
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {

    /**
     * 不需要拦截的请求
     */
	private List<String> urls;
}
