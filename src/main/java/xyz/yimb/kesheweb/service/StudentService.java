package xyz.yimb.kesheweb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xyz.yimb.kesheweb.entity.Student;

import java.util.List;

public interface StudentService {

    /**
     * 根据学号和密码查询学生
     * @param account 学号
     * @param password 密码
     * @return 找到返回学生对象，否则null
     */
    public Student getByAccountAndPassword(String account, String password);

    Page<Student> getMajorAndCollege(Pageable pageable);
}
