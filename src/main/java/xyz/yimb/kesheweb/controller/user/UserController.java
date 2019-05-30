package xyz.yimb.kesheweb.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.yimb.kesheweb.entity.User;
import xyz.yimb.kesheweb.service.UserService;

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
}
