package xyz.yimb.kesheweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login.html").setViewName("/index");
        registry.addViewController("/user/importPre").setViewName("/user/import");
        registry.addViewController("/user/searchPre").setViewName("/user/searchupdate");
        registry.addViewController("/user/sete").setViewName("/user/searchupdate");
        registry.addViewController("/user/resPre").setViewName("/user/recol");
        registry.addViewController("/user/resMajor").setViewName("/user/resmajor");
    }
}
