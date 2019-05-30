package xyz.yimb.kesheweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class Welcome {

    @RequestMapping("welcome")
    public String welcome(HttpServletRequest request){
        return "welcome";
    }
}
