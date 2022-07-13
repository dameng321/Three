package com.dameng.common.security.config;

import com.dameng.common.core.utils.RedisUtil;
import com.dameng.common.security.filter.AuthorizeFilter;
import com.dameng.common.security.filter.LoginFilter;
import com.dameng.common.security.handler.DefaultPasswordEncoder;
import com.dameng.common.security.handler.JwtLogoutHandler;
import com.dameng.common.security.handler.SimpleAccessDeniedHandler;
import com.dameng.common.security.handler.UnAuthEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Description: SpringSecurity配置类</p>
 *
 * @author dameng
 * @version v1.0.0
 * @since 2021/5/30 17:18
 **/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private RedisUtil redisUtil;
    private DefaultPasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Autowired
    public SecurityConfig(RedisUtil redisUtil, DefaultPasswordEncoder passwordEncoder, UserDetailsService userDetailsService,
                          IgnoreUrlsConfig ignoreUrlsConfig) {
        this.redisUtil = redisUtil;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.ignoreUrlsConfig = ignoreUrlsConfig;
    }

    /**
     * Description： // 调用userDetailsService和密码处理
     * @param auth 1
     * @version 0.1.0
     * @return void
     * @author dameng
     * @date 2021/5/30 17:26
     * @since 0.1.0
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.warn("SecurityConfig: auth配置  调用userDetailsService和密码处理");

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


//    /**
//     * Description： 设置不需要认证的路径
//     * @param web 1
//     * @version 0.1.0
//     * @return void
//     * @author dameng
//     * @date 2021/5/30 17:29
//     * @since 0.1.0
//     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        log.warn("SecurityConfig: 设置不需要认证的路径");
//        web.ignoring().antMatchers(getUrls());
//    }

    public String[] getUrls() throws Exception {
        log.warn("SecurityConfig: 设置不需要认证的路径");

        String[] fixUrls = {"/api/**","/swagger-resources/**","/v2/**","/v3/**","/webjars/**","/doc.html/**"
        };
        //从配置文件中加载每个模块配置的路径
        List<String> diyUrls = ignoreUrlsConfig.getUrls();
        if (diyUrls==null){
            return fixUrls;
        }else {
            List<String> fixUrlsList = Arrays.asList(fixUrls);
            diyUrls.addAll(fixUrlsList);
            String[] urls = diyUrls.toArray(diyUrls.toArray(new String[0]));
            return urls;
        }

    }

    /**
     * Description： 配置
     * @param http 1
     * @version 0.1.0
     * @return void
     * @author dameng
     * @date 2021/5/30 17:27
     * @since 0.1.0
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.warn("SecurityConfig: http配置");

        http.exceptionHandling()
                //设置没有权限处理
                .authenticationEntryPoint(new UnAuthEntryPoint())
                //设置不允许访问处理类
                .accessDeniedHandler(new SimpleAccessDeniedHandler())
                .and().csrf().disable()
                .authorizeRequests()
                //放行请求
                .antMatchers(getUrls()).permitAll()
                .anyRequest().authenticated()
                //设置退出登录路径
                //.and().logout().logoutUrl("/admin/logout")
                //设置退出登录处理器
                //.addLogoutHandler(new JwtLogoutHandler(redisUtil)).and()
                //设置认证拦截器
                //.addFilter(new LoginFilter(authenticationManager(),redisUtil))
                //设置授权拦截器
                .and().addFilter(new AuthorizeFilter(authenticationManager(),redisUtil)).httpBasic();
        http.cors().configurationSource(corsConfigurationSource());
    }

    private CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source =   new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
        corsConfiguration.addAllowedOrigin("*");
        //header，允许哪些header，本案中使用的是token，此处可将*替换为token；
        corsConfiguration.addAllowedHeader("*");
        //允许的请求方法，PSOT、GET等
        corsConfiguration.addAllowedMethod("*");
        //配置允许跨域访问的url
        source.registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }

}
