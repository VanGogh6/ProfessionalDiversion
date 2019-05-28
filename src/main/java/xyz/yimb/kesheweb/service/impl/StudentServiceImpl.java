package xyz.yimb.kesheweb.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.yimb.kesheweb.entity.Student;
import xyz.yimb.kesheweb.repository.StudentRepository;
import xyz.yimb.kesheweb.service.StudentService;
import xyz.yimb.kesheweb.utils.WebUtils;

import java.io.InputStream;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student getByAccountAndPassword(String account, String password) {
        return studentRepository.getByAccountAndPassword(account, WebUtils.getMD5(password));
    }

    @Override
    public Page<Student> getMajorAndCollege(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public boolean deleteStuBySid(String sid) {
        try {
            int i = Integer.parseInt(sid);
            studentRepository.deleteById(i);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteBatch(String[] sids) {
        try {
            for (int i = 0; i <sids.length ; i++) {
                String str=sids[i];
                int sid = Integer.parseInt(str);
                studentRepository.deleteById(sid);
            }
        }catch(Exception e){
            System.out.println("msg="+e.getMessage());
            return false;
        }
       return true;
    }

    @Override
    public boolean importData(InputStream is,String filename) {
        List<Student> list=WebUtils.getStudentList(is,filename);
        List<Student> students = studentRepository.saveAll(list);
        if (students.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

}
