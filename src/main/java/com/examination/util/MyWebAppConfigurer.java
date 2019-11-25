package com.examination.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        registry.addInterceptor(new CaptchaInterceptor()).addPathPatterns("/doLogin");
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login","/","/defaultKaptcha","/doLogin","/wrongpassword");
//        registry.addInterceptor(new StudentInterceptor()).addPathPatterns("/student/**");
//        registry.addInterceptor(new TeacherInterceptor()).addPathPatterns("/teacher/**");
//        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/admin/**");
//    }
//
//    /**
//     * 解决resources下面静态资源无法访问
//     * @param registry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/favicon.ico")//favicon.ico
//                .addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/resources/static/");
//        super.addResourceHandlers(registry);
//    }
}
