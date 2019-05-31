package xyz.yimb.kesheweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.yimb.kesheweb.entity.Info;
import xyz.yimb.kesheweb.service.InfoService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class InfoController {

    @Autowired
    private InfoService infoServiceImpl;

    @RequestMapping("infolist")
    public String infoList(HttpServletRequest request){
        List<Info> list=infoServiceImpl.getAll();
        request.setAttribute("list",list);
        return "/user/user/infolist";
    }
}
