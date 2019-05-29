package xyz.yimb.kesheweb.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.yimb.kesheweb.entity.College;
import xyz.yimb.kesheweb.service.CollegeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("user/college")
public class CollegeController {

    @Autowired
    private CollegeService collegeServiceImpl;

    @RequestMapping("collegelist")
    public String collegeList(HttpServletRequest request){
        List<College> list = collegeServiceImpl.getAll();
        request.setAttribute("list",list);
        return "forward:/user/college/list";
    }

    @RequestMapping("delete")
    public String deleteCollege(@Param("cid") Integer cid,HttpServletRequest request){
        boolean b=collegeServiceImpl.deleteCollege(cid);
        if (b){
            request.setAttribute("msg","删除成功");
        }else{
            request.setAttribute("msg","删除失败");
        }
        return "forward:collegelist";
    }

    @RequestMapping("insert")
    public String insert(College college,HttpServletRequest request){
        boolean b=collegeServiceImpl.insertCollege(college);
        if (b){
            request.setAttribute("msg","添加成功");
        }else{
            request.setAttribute("msg","添加失败");
        }
        return "forward:collegelist";
    }
}
