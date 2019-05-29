package xyz.yimb.kesheweb.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.yimb.kesheweb.entity.College;
import xyz.yimb.kesheweb.entity.Major;
import xyz.yimb.kesheweb.service.CollegeService;
import xyz.yimb.kesheweb.service.MajorService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("user/major")
public class UserMajorController {

    @Autowired
    private CollegeService collegeServiceImpl;

    @Autowired
    private MajorService majorServiceImpl;

    @RequestMapping("listAll")
    public String listAll(@Param("cid") Integer cid, HttpServletRequest request){
        if (cid==null){
            cid=1;
        }
        List<College> colleges = collegeServiceImpl.getAll();
        College col = collegeServiceImpl.getCollegeByCid(cid);
        List<Major> majors = col.getMajors();
        request.setAttribute("colleges",colleges);
        request.setAttribute("majors",majors);
        request.setAttribute("cid",cid);
        return "forward:/user/major/list";
    }

    @RequestMapping("insertPre")
    public String insertPre(HttpServletRequest request){
        List<College> colleges = collegeServiceImpl.getAll();
        request.setAttribute("colleges",colleges);
        return "forward:/user/major/insert";
    }

    @RequestMapping("insertmajor")
    public  String insertMajor(HttpServletRequest request){
        String cid = request.getParameter("cid");
        String name = request.getParameter("name");
        String number = request.getParameter("number");
        boolean b=majorServiceImpl.insertMajorByCid(name,number,cid);
        if (b){
            request.setAttribute("msg","添加成功");
        }else{
            request.setAttribute("msg","添加失败");
        }
        return "forward:insertPre";
    }

    @RequestMapping("delete")
    public String deleteMajor(@Param("mid") Integer mid,@Param("cid") Integer cid,HttpServletRequest request){
        boolean b=majorServiceImpl.deleteMajorByMid(mid);
        if (b){
            request.setAttribute("msg","删除成功");
            request.setAttribute("cid",cid);
        }
        return "forward:listAll";
    }

    @RequestMapping("updatePre")
    public String updatePre(@Param("mid") Integer mid,@Param("cid") Integer cid,HttpServletRequest request){
        Major major=majorServiceImpl.getMajorByMid(mid);
        request.setAttribute("cid",cid);
        request.setAttribute("major",major);
        return "forward:updatenumber";
    }

    @RequestMapping("updateNumber")
    public String updateNumber(@Param("cid") Integer cid,@Param("mid") Integer mid,@Param("number") Integer number,HttpServletRequest request){
        boolean b=majorServiceImpl.updateMjorNumberByCid(number,mid);
        if (b){
            request.setAttribute("msg","修改成功");
        }
        request.setAttribute("cid",cid);
        return "forward:listAll";
    }

}
