package xyz.yimb.kesheweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.yimb.kesheweb.entity.Student;
import xyz.yimb.kesheweb.entity.User;
import xyz.yimb.kesheweb.service.StudentService;
import xyz.yimb.kesheweb.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private StudentService studentServiceImpl;

    @PostMapping("login")
    public String login(String account, String password, HttpServletRequest req){
        User user = userServiceImpl.getByAccountAndPassword(account, password);
        Student student = studentServiceImpl.getByAccountAndPassword(account, password);
        if (user!=null&&student==null){
            req.getSession().setAttribute("uid",user.getUid());
            req.getSession().setAttribute("user",user);
            req.getSession().setAttribute("username", user.getName());
            return "redirect:/user/index";
        }else if (student!=null&&user==null){
            req.getSession().setAttribute("sid",student.getSid());
            req.getSession().setAttribute("student",student);
            req.getSession().setAttribute("username", student.getName());
            return "redirect:/student/index";
        }
        req.setAttribute("msg","账号或密码错误");
        return "index";
    }

    //loginout
    @RequestMapping("loginout")
    public String loginout(HttpServletRequest request){
        request.getSession().invalidate();;
        return "index";
    }
}
