package com.wudu.Roll.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @program: Roll
 * @description: 跨域配置类
 * @create: 2023-7-11 10:35
 **/
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration corsConfig=new CorsConfiguration();
        corsConfig.addAllowedOrigin("*");//允许所有域名跨域访问
        corsConfig.addAllowedMethod("*");//允许所有请求跨域访问
        corsConfig.addAllowedHeader("*");//允许所有请求头跨域访问
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfig);//对所有请求路径进行跨域设置
        return new CorsFilter(source);
    }
}
