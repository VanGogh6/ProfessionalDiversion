package xyz.yimb.kesheweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import xyz.yimb.kesheweb.entity.Student;
import xyz.yimb.kesheweb.repository.StudentRepository;
import xyz.yimb.kesheweb.service.StudentService;
import xyz.yimb.kesheweb.utils.WebUtils;

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
    public void deleteStuBySid(String sid) {
        try {
            int i = Integer.parseInt(sid);
            studentRepository.deleteById(i);
        }catch(Exception e){

        }
    }

}
