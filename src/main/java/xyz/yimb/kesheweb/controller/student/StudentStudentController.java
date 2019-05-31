package xyz.yimb.kesheweb.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.yimb.kesheweb.entity.College;
import xyz.yimb.kesheweb.entity.Major;
import xyz.yimb.kesheweb.entity.Student;
import xyz.yimb.kesheweb.service.MajorService;
import xyz.yimb.kesheweb.service.StudentService;
import xyz.yimb.kesheweb.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentStudentController {

    @Autowired
    private StudentService studentServiceImpl;

    @Autowired
    private MajorService majorServiceImpl;

    @RequestMapping("wishPre")
    public String wishPre(HttpServletRequest request){
        Integer sid=(Integer)request.getSession().getAttribute("sid");
        Student stu = studentServiceImpl.getStuBySid(sid);
        College college = stu.getCollege();
        if (college!=null){
            request.setAttribute("college","yes");
            List<Major> majors = college.getMajors();
            request.setAttribute("majors",majors);
        }else if(college==null) {
            request.setAttribute("msg","你还没有分配到相应的学院，无法选择专业，请联系学校处理");
        }
        request.setAttribute("stu",stu);
        return "forward:/student/updateWish";
    }

    @RequestMapping("update")
    public String updateWish(HttpServletRequest req){
        Integer sid=(Integer)req.getSession().getAttribute("sid");
        String mid1 = req.getParameter("mid1");
        String mid2 = req.getParameter("mid2");
        String mid3 = req.getParameter("mid3");
        String mid4 = req.getParameter("mid4");
        String wish=mid1+":"+mid2+":"+mid3+":"+mid4+":";
        boolean b=studentServiceImpl.updateWish(wish,sid);
        if (b){
            req.setAttribute("msg","填报成功!");
        }
        return "forward:wishPre";
    }

    @RequestMapping("infoPre")
    public String infoPre(HttpServletRequest req){
        Integer sid=(Integer)req.getSession().getAttribute("sid");
        Student stu = studentServiceImpl.getStuBySid(sid);
        req.setAttribute("stu",stu);
        String wishs = stu.getWish();
        if (wishs!=null){
            String[] sp = wishs.split(":");
            Integer mid1=Integer.parseInt(sp[0]);
            Major major1 = majorServiceImpl.getMajorByMid(mid1);//志愿1
            req.setAttribute("info1",major1.getName());
            Integer mid2=Integer.parseInt(sp[1]);
            Major major2 = majorServiceImpl.getMajorByMid(mid2);//志愿2
            req.setAttribute("info2",major2.getName());
            Integer mid3=Integer.parseInt(sp[2]);
            Major major3 = majorServiceImpl.getMajorByMid(mid3);//志愿3
            req.setAttribute("info3",major3.getName());
            Integer mid4=Integer.parseInt(sp[3]);
            Major major4 = majorServiceImpl.getMajorByMid(mid4);//志愿4
            req.setAttribute("info4",major4.getName());
        }else if(wishs==null || "".equals(wishs)){
            req.setAttribute("msg","你还未选择专业");
        }
        return "forward:/student/wishinfo";
    }

    @RequestMapping("updPre")
    public String updPre(HttpServletRequest req){
        Integer sid=(Integer)req.getSession().getAttribute("sid");
        Student stu = studentServiceImpl.getStuBySid(sid);
        req.setAttribute("stu",stu);
        return "forward:/student/updstu";
    }
    @RequestMapping("upd")
    public String upd(@Param("name") String name,@Param("phone") String phone,HttpServletRequest req){
        Integer sid=(Integer)req.getSession().getAttribute("sid");
        boolean b=studentServiceImpl.updNameAndPhone(name,phone,sid);
        return "forward:infoPre";
    }

    @RequestMapping("updpass")
    public String updPass(@Param("pwd") String pwd,@Param("pwd1") String pwd1,HttpServletRequest request){
        Student stu=(Student)request.getSession().getAttribute("student");
        String pw0 = WebUtils.getMD5(pwd);
        if (pw0.equals(stu.getPassword())){
            Integer sid = stu.getSid();
            String pw= WebUtils.getMD5(pwd1);
            boolean b=studentServiceImpl.updPwd(pw,sid);
            if (b){
                request.setAttribute("msg","修改密码成功");
            }
        }else {
            request.setAttribute("msg","修改密码失败,原密码错误!");
        }
        return "forward:/student/updpwd";
    }
}
