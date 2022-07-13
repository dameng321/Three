package com.dameng.common.core.config.feign;//package com.dameng.base.config.feign;
//
//import com.dameng.base.consts.TokenConst;
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
///**
// * <p>Description: Feign 远程调用配置类</p>
// *
// * @author dameng
// * @version v1.0.0
// * @since 2021/5/26 10:32
// **/
//
//
//@Configuration
//public class FeignConfiguration implements RequestInterceptor {
//
//private final Logger logger = LoggerFactory.getLogger(getClass());
//
//@Override
//public void apply(RequestTemplate template) {
//    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
//            .getRequestAttributes();
//    HttpServletRequest request = attributes.getRequest();
//    String authorization = request.getHeader(TokenConst.TOKEN_NAME);
//
//    template.header(TokenConst.TOKEN_NAME, authorization);
//}
//}
