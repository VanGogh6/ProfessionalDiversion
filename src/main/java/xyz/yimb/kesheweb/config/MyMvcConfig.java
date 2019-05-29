package xyz.yimb.kesheweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login.html").setViewName("/index");
        registry.addViewController("/user/importPre").setViewName("/user/student/import");
        registry.addViewController("/user/searchPre").setViewName("/user/student/searchupdate");
        registry.addViewController("/user/sete").setViewName("/user/student/searchupdate");
        registry.addViewController("/user/resPre").setViewName("/user/student/recol");
        registry.addViewController("/user/resMajor").setViewName("/user/student/resmajor");
        registry.addViewController("/user/college/list").setViewName("/user/college/list");
        registry.addViewController("/user/college/insertPre").setViewName("/user/college/insert");
        registry.addViewController("/user/major/list").setViewName("/user/major/list");
        registry.addViewController("/user/major/insert").setViewName("/user/major/insert");
        registry.addViewController("/user/major/updatenumber").setViewName("/user/major/updatenumber");
    }
}
