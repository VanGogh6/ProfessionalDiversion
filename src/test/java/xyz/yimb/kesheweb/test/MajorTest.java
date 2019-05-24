package xyz.yimb.kesheweb.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.yimb.kesheweb.entity.College;
import xyz.yimb.kesheweb.entity.Major;
import xyz.yimb.kesheweb.entity.Student;
import xyz.yimb.kesheweb.repository.MajorRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MajorTest {

    @Autowired
    private MajorRepository majorRepository;

    /**
     * 单向多对一查询测试
     */
    @Test
    @Transactional
    public void t1(){
        Major m = majorRepository.getOne(1);
        System.out.println(m);
        College c = m.getCollege();
        System.out.println(c);
    }


    /**
     * 单项一对多测试
     */
    @Test
    @Transactional
    public void t2(){
        Major m = majorRepository.getOne(1);
        System.out.println(m);
        List<Student> students = m.getStudents();
        for (int i = 0; i <students.size() ; i++) {
            Student student = students.get(i);
            System.out.println(student);
        }
    }
}
