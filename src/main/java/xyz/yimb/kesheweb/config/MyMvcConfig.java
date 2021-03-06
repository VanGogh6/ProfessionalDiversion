package xyz.yimb.kesheweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.yimb.kesheweb.component.LoginHandlerInterceptor;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin").setViewName("/index");
        registry.addViewController("/index").setViewName("/index");
        registry.addViewController("/login.html").setViewName("/index");
        registry.addViewController("/user/index").setViewName("/user/index");
        registry.addViewController("/welcome").setViewName("/welcome");
        registry.addViewController("/user/user/one").setViewName("/user/user/one");
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
        registry.addViewController("/user/major/auto").setViewName("/user/major/auto");
        registry.addViewController("/user/user/list").setViewName("/user/user/list");
        registry.addViewController("/user/updPre").setViewName("/user/user/upd");
        registry.addViewController("/student/index").setViewName("/student/index");
        registry.addViewController("/student/updateWish").setViewName("/student/updateWish");
        registry.addViewController("/student/wishinfo").setViewName("/student/wishinfo");
        registry.addViewController("/student/updstu").setViewName("/student/updstu");
        registry.addViewController("/student/updPwdPre").setViewName("/student/updpwd");
        registry.addViewController("/student/updpwd").setViewName("/student/updpwd");
        registry.addViewController("/user/user/infolist").setViewName("/user/user/infolist");
    }

    /**
     * 登录拦截
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/login/**","/index/**","/welcome/**","/login.html","/admin/**","/loginout/**","/webjars/**","/asserts/**");
    }
}
