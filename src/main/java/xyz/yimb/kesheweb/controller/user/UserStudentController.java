package xyz.yimb.kesheweb.controller.user;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import xyz.yimb.kesheweb.entity.College;
import xyz.yimb.kesheweb.entity.Student;
import xyz.yimb.kesheweb.service.CollegeService;
import xyz.yimb.kesheweb.service.StudentService;
import xyz.yimb.kesheweb.utils.WebUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;


@Controller
@RequestMapping("user")
public class UserStudentController {
    @Autowired
    private StudentService studentServiceImpl;

    @Autowired
    private CollegeService collegeServiceImpl;

    @RequestMapping("studentlist")
    public String getStudentList(String page, String size, HttpServletRequest req){
        String str="";
        if (page==""|| page==null){
            str="1";//默认第一页
        }else if (page.contains(",")){
            try {
                str=page.split(",")[1];
            }catch(Exception e){//用户跳转未输入操作异常
                str="1";
            }
        }else {
            str=page;
        }
        if (size==""||size==null){
            size="6";
        }
        int p=0;
        int s=0;
        System.out.println("str:"+str);
        try {
            p=Integer.parseInt(str)-1;
            s=Integer.parseInt(size);
        }catch (Exception e){//处理用户输入非方法字符异常
            System.err.println(e.getMessage());
            p=0;
            s=6;
        }
        if (p<0){//用户输入小于等于0的异常，默认第一页
            p=0;
        }
        Pageable pageable=new QPageRequest(p,s);
        Page<Student> list= studentServiceImpl.getMajorAndCollege(pageable);
        int number = list.getNumber();
        req.setAttribute("page",(number+1));//第几页
        int totalPages = list.getTotalPages();
        req.setAttribute("pages",totalPages);//更新总页数
        int elements = list.getNumberOfElements();
        req.setAttribute("elements",elements);//每页记录数
        long totals = list.getTotalElements();
        req.setAttribute("totals",totals);//总条数
        List<Student> studentList = list.getContent();
        req.setAttribute("studentList",studentList);
        return "/user/student/studentlist";
    }

    /**
     * 单个删除
     * @param sid
     * @param request
     * @return
     */
    @RequestMapping("delete")
    public String delete(String sid,HttpServletRequest request){
        boolean b = studentServiceImpl.deleteStuBySid(sid);
        if (b){
            request.setAttribute("msg","删除成功");
        }
        return "forward:studentlist";
    }

    /**
     * 批量删除
     * @param request
     * @return
     */
    @RequestMapping("deletebatch")
    public  String  deletebatch(HttpServletRequest request){
        String[] sids = request.getParameterValues("selectFlag");
        if (studentServiceImpl.deleteBatch(sids)){
            request.setAttribute("msg","删除成功");
        }else {
            request.setAttribute("msg","删除失败");
        }
        return "forward:studentlist";
    }

    /**
     * 导入数据
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("import")
    public String importStudent(@RequestParam("file")   MultipartFile file, HttpServletRequest request){
        String filename = file.getOriginalFilename();
        try {
            InputStream is = file.getInputStream();
            boolean b = studentServiceImpl.importData(is,filename);
            if (b){
                request.setAttribute("msg","添加成功，学生默认密码为1，请及时通知学生登录修改密码");
            }else {
                request.setAttribute("msg","导入失败");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "forward:studentlist";
    }

    /**
     * 导出数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("export")
    public String exportPre(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + "data.xlsx");
            List<Student> list=studentServiceImpl.getAllStudent();
            XSSFWorkbook wb= WebUtils.getHSSFWorkbook(list);
            ServletOutputStream out = response.getOutputStream();
            wb.write(out);
            out.close();
        } catch (Exception e) {

        }
        return "forward:studentlist";
    }

    /**
     * 按学号查找学生
     * @param account
     * @param request
     * @return
     */
    @RequestMapping("search")
    public String search(@Param("account") String account,HttpServletRequest request){
        Student student=studentServiceImpl.getStuByAccount(account);
        System.out.println(student);
        request.setAttribute("student",student);
        return "forward:/user/sete";
    }

    /**
     * 重置密码
     * @param sid
     * @param request
     * @return
     */
    @RequestMapping("resetPwd")
    public String resetPwd(@Param("sid")Integer sid,HttpServletRequest request){
        boolean b=studentServiceImpl.resetPwd(sid);
        Student student=studentServiceImpl.getStuBySid(sid);
        System.out.println(student);
        request.setAttribute("student",student);
        if (b){
            request.setAttribute("msg","重置密码成功！");
        }else {
            request.setAttribute("msg","重置密码失败！");
        }
        return "forward:/user/sete";
    }

    /**
     * 重置学院前置操作
     * @param sid
     * @param request
     * @return
     */
    @RequestMapping("resetColPre")
    public String resetColPre(@Param("sid")Integer sid,HttpServletRequest request){
        Student student = studentServiceImpl.getStuBySid(sid);
        List<College> list=collegeServiceImpl.getAll();
        request.setAttribute("student",student);
        request.setAttribute("list",list);
        return "forward:/user/resPre";
    }

    /**
     * 重置学院
     * @param sid
     * @param cid
     * @param request
     * @return
     */
    @RequestMapping("resetcollege")
    public String resetcollege(@Param("sid") Integer sid,@Param("cid") Integer cid,HttpServletRequest request){
        Student student = studentServiceImpl.getStuBySid(sid);
        List<College> list=collegeServiceImpl.getAll();
        request.setAttribute("student",student);
        request.setAttribute("list",list);
        boolean b=studentServiceImpl.updateCid(sid,cid);
        boolean b2=studentServiceImpl.updateMidBySid(null,sid);//转院时清空自己的专业
        if (b&&b2){
            request.setAttribute("msg","成功");
        }else {
            request.setAttribute("msg","失败");
        }
        String account=student.getAccount();
        return "forward:/user/search?account="+account;
    }

    /**
     * 转专业前置
     * @param sid
     * @param request
     * @return
     */
    @RequestMapping("resetMajorPre")
    public String resetMajorPre(@Param("sid") Integer sid,HttpServletRequest request){
        Student student = studentServiceImpl.getStuBySid(sid);
        request.setAttribute("student",student);
        return "forward:/user/resMajor";
    }

    /**
     * 转专业
     * @param sid
     * @param mid
     * @param request
     * @return
     */
    @RequestMapping("resetmajor")
    public String resetMajor(@Param("sid") Integer sid,@Param("mid") Integer mid,HttpServletRequest request){
        boolean b=studentServiceImpl.updateMidBySid(mid,sid);
        if (b){
            request.setAttribute("msg","成功");
        }else {
            request.setAttribute("msg","失败");
        }
        Student student = studentServiceImpl.getStuBySid(sid);
        String account=student.getAccount();
        return "forward:/user/search?account="+account;
    }
}
