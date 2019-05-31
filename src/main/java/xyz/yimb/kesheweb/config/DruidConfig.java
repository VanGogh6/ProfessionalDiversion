package xyz.yimb.kesheweb.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置，采用阿里巴巴的druid
 */
@Configuration
public class DruidConfig {

    //配置druid的监控
    //1.配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean=new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> m=new HashMap<>();
        m.put("loginUsername","admin");//配置druid后台管理得登录名
        m.put("loginPassword","1");//密码
        m.put("allow","");
        bean.setInitParameters(m);//设置添加
        return bean;//loginUsername
    }
    //2.配置一个web监控得filter
    @Bean
    public FilterRegistrationBean webViewServlet(){
        FilterRegistrationBean bean=new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String,String> m=new HashMap<>();
        m.put("exclusions","*.js,*.css,/druid/*");//排除不拦截得页面
        bean.setInitParameters(m);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }

}