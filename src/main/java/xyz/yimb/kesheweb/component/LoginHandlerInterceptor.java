package xyz.yimb.kesheweb.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.yimb.kesheweb.entity.Student;
import xyz.yimb.kesheweb.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//用拦截器制作登录拦截
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        String servletPath = request.getServletPath();//请求地址
        String path=request.getContextPath();//上下文项目路径
        User user=(User)session.getAttribute("user");
        Student student=(Student)session.getAttribute("student");
        if (user!=null){
            return true;
        }else if(student!=null){
            return true;
        }else {
            request.setAttribute("msg","请先登录");
            response.sendRedirect(path+"/");//重定向
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}