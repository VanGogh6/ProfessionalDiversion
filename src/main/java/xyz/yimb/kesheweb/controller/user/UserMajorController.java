package xyz.yimb.kesheweb.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.yimb.kesheweb.entity.College;
import xyz.yimb.kesheweb.entity.Major;
import xyz.yimb.kesheweb.entity.Student;
import xyz.yimb.kesheweb.service.CollegeService;
import xyz.yimb.kesheweb.service.MajorService;
import xyz.yimb.kesheweb.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user/major")
public class UserMajorController {

    @Autowired
    private CollegeService collegeServiceImpl;

    @Autowired
    private StudentService studentServiceImpl;

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

    /**
     * 自动分配专业
     * 1.扫描所有专业人数未招满的专业，被填写该专业为第一志愿的学生
     * 2.扫描未被录取，专业人数未招满的专业，填写该专业第二志愿的学生
     * 3.扫描未被录取，专业人数未招满的专业，填写该专业第三志愿的学生
     * 4.扫描未被录取，专业人数未招满的专业，填写该专业第四志愿的学生
     * 先找到专业未录取满的专业，再找到填写该专业未被录取的学生，对这一部分学生按成绩排序，从高到低录取
     * @return
     */
    @RequestMapping("automajor")
    public String autoMajor(HttpServletRequest request){
        for (int f = 0; f <4 ; f++) {
            System.out.println("第"+(f+1)+"志愿录取中");
            //一:第f+1志愿录取过程
            //1.获取所有专业majors，判断是否(nownumber<number)招满?未招满加入集合。
            List<Major> list = majorServiceImpl.getAll();//获取所有专业
            List<Major> majors=new ArrayList<>();//存没有录取满的所有专业
            for (int i = 0; i <list.size() ; i++) {
                Major major = list.get(i);
                if (major.getNownumber()<major.getNumber()){
                    majors.add(major);
                }
            }
            //2.依次取出集合majors中的专业major，查找填写第一志愿的学生(且没有被录取)sp[0]==mid，把该学生存入集合，按成绩grade排序，每次录取都判断nownumber<number-->录取学生则-->nownumber++，更新学生的mid
            for (int i = 0; i <majors.size() ; i++) {
                Major major = majors.get(i);
                Integer nownum = major.getNownumber();
                Integer num = major.getNumber();
                Integer mid = major.getMid();
                List<Student> students = studentServiceImpl.getAllStudent();
                List<Student> studentList= new ArrayList<>();//生成空的学生容器,存取准备录取的学生
                for (int j = 0; j < students.size(); j++) {
                    Student student = students.get(j);
                    Integer mid1=null;
                    Major major1 = student.getMajor();
                    if (major1!=null){
                        mid1= major1.getMid();
                    }
                    if (mid1==null){//该学生未被录取
                        String wish = student.getWish();
                        if (wish!=null && wish!=""){//该学生已经填写志愿
                            String[] sp = wish.split(":");
                            if (sp[f].equals(mid+"")){//判断学生的第1+(f=0)志愿与专业进行匹配
                                studentList.add(student);//容器添加学生
                            }
                        }
                    }
                }
                studentList.sort((x,y) ->Double.compare(y.getGrade(),x.getGrade()));//成绩降序排序
                for (int j = 0; j < studentList.size(); j++) {//开始正式录取
                    Student student = studentList.get(j);
                    System.out.println("mid=="+student.getMid());
                    if (nownum<num){
                        //修改学生mid为当前专业mid
                        boolean b = studentServiceImpl.updateMidBySid(mid, student.getSid());
                        if (b){
                            nownum++;
                        }
                    }else{
                        break;//专业人数已满
                    }
                }
                Integer nownumber=nownum;
                boolean b=majorServiceImpl.updateMjorNowNmber(nownumber,mid);//更新录取完当前志愿的已录取专业人数
                if (b){
                    request.setAttribute("msg","自动分配专业成功!");
                }
            }
            //3.等待所有专业第f+1志愿录取完成
        }
        return "forward:/user/major/auto";
    }


}
