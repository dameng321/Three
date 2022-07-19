package com.dameng.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>Description: 系统模块启动类</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2022-7-12 17:08
 **/

@SpringBootApplication(scanBasePackages = "com.dameng.*")
@MapperScan("com.dameng.*.mapper")
@EnableFeignClients
@EnableDiscoveryClient
public class ThreeSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreeSystemApplication.class,args);
    }
}
