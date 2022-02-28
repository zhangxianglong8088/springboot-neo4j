//package com.dalaoyang.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.ResourceUtils;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @description：
// * @author: zhangxianglong
// * @date: 2022/2/28
// */
//@Configuration
//public class WebMvcConfg extends WebMvcConfigurationSupport {
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
//        super.addResourceHandlers(registry);
//    }
//}
