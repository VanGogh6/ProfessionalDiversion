package xyz.yimb.kesheweb.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.yimb.kesheweb.entity.Student;
import xyz.yimb.kesheweb.service.StudentService;


@Controller
@RequestMapping("user")
public class UserStudentController {
    @Autowired
    private StudentService studentServiceImpl;

    @RequestMapping("studentlist")
    public String getStudentList(String page,String size){
        if (page==""|| page==null){
            page="1";
        }
        int p=Integer.parseInt(page)-1;
        if (size==""||size==null){
            size="5";
        }
        int s=Integer.parseInt(size);
        Pageable pageable=new QPageRequest(p,s);
        Page<Student> list= studentServiceImpl.getMajorAndCollege(pageable);
        return "/user/studentlist";
    }
}
