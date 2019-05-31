package xyz.yimb.kesheweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.yimb.kesheweb.entity.Info;
import xyz.yimb.kesheweb.entity.Student;
import xyz.yimb.kesheweb.entity.User;
import xyz.yimb.kesheweb.service.InfoService;
import xyz.yimb.kesheweb.service.StudentService;
import xyz.yimb.kesheweb.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private StudentService studentServiceImpl;

    @Autowired
    private InfoService infoServiceImpl;

    @PostMapping("login")
    public String login(String account, String password, HttpServletRequest req){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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


    @RequestMapping("loginout")
    public String loginout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/index";
    }
}
