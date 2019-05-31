package xyz.yimb.kesheweb.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.yimb.kesheweb.entity.User;
import xyz.yimb.kesheweb.service.UserService;
import xyz.yimb.kesheweb.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @RequestMapping("listPre")
    public String listPre(HttpServletRequest request){
        List<User> list=userServiceImpl.getAll();
        request.setAttribute("list",list);
        return "forward:/user/user/list";
    }

    @RequestMapping("updpass")
    public String updPass(@Param("pwd") String pwd, @Param("pwd1") String pwd1, HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        Integer uid = user.getUid();
        String pw0 = user.getPassword();
        String pw = WebUtils.getMD5(pwd);
        if (pw0.equals(pw)){
            String password = WebUtils.getMD5(pwd1);
            boolean b=userServiceImpl.updPwd(password,uid);
            if (b){
                request.setAttribute("msg","修改密码成功");
            }
        }else {
            request.setAttribute("msg","原密码错误");
        }
        return "forward:/user/updPre";
    }
}
