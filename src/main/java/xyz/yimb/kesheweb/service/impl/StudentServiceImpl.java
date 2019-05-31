package xyz.yimb.kesheweb.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Override
    public Student getStuByAccount(String account) {
        return studentRepository.getByAccount(account);
    }

    @Override
    @Transactional
    public boolean resetPwd(Integer sid) {
        String pwd=WebUtils.getMD5("1");
        int i=studentRepository.updatePwd(pwd,sid);
        return i>0;
    }

    @Override
    public Student getStuBySid(Integer sid) {
        return studentRepository.getOne(sid);
    }

    @Override
    @Transactional
    public boolean updateCid(Integer sid, Integer cid) {
        int i=studentRepository.updateCid(sid,cid);
        return i>0;
    }

    @Override
    @Transactional
    public boolean updateMidBySid(Integer mid, Integer sid){
        int i=studentRepository.updateMidBySid(mid,sid);
        return i>0;
    }

    @Override
    @Transactional
    public boolean updateWish(String wish, Integer sid) {
        int i=studentRepository.updateWish(wish,sid);
        return i>0;
    }

    @Override
    @Transactional
    public boolean updNameAndPhone(String name, String phone, Integer sid) {
        int i=studentRepository.updNameAndPhone(name,phone,sid);
        return i>0;
    }

    @Override
    @Transactional
    public boolean updPwd(String pw, Integer sid) {
        int i = studentRepository.updatePwd(pw, sid);
        return i>0;
    }


}
