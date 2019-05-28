package xyz.yimb.kesheweb.controller.user;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import xyz.yimb.kesheweb.entity.Student;
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
        return "/user/studentlist";
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

    //export
    @RequestMapping("export")
    public String exportPre(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + "data.xlsx");
        List<Student> list=studentServiceImpl.getAllStudent();
        XSSFWorkbook wb= WebUtils.getHSSFWorkbook(list);
        try {
            ServletOutputStream out = response.getOutputStream();
            wb.write(out);
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "forward:studentlist";
    }

}
