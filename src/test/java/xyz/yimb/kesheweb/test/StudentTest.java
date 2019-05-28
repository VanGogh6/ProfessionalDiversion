package xyz.yimb.kesheweb.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.yimb.kesheweb.entity.College;
import xyz.yimb.kesheweb.entity.Major;
import xyz.yimb.kesheweb.entity.Student;
import xyz.yimb.kesheweb.repository.StudentRepository;
import xyz.yimb.kesheweb.service.StudentService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTest {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentServiceImpl;

    //查出学生 直接查改学生的所在学院
    @Test
    @Transactional
    public void t1(){
        Student student = studentRepository.getOne(1);
        System.out.println(student);
        College c = student.getCollege();
        Pageable pageable=new PageRequest(5,10);
        studentRepository.findAll();
        System.out.println(c);
    }

    //一对多测试  查出学院，  查该学院的所有学生
    @Test
    @Transactional
    public void t2(){
        Student student = studentRepository.getOne(1);
        System.out.println(student);
        College c = student.getCollege();
        List<Student> students = c.getStudents();
        for (int i = 0; i <students.size() ; i++) {
            System.out.println(students.get(i));
        }
    }

    //三表级联查询测试  查询到学生-->查询专业-->查询学院
    @Test
    @Transactional
    public void t3(){
        Student s = studentRepository.getOne(3);
        System.out.println(s);
        if (s!=null){
            Major m = s.getMajor();
            System.out.println(m);
            if (m!=null){
                College c = m.getCollege();
                System.out.println(c);
            }
        }
    }

    //分页查询测试
    @Test
    @Transactional
    public void t4(){
        Pageable pageable=new QPageRequest(0,5);
        Page<Student> pages = studentRepository.findAll(pageable);
        List<Student> list = pages.getContent();
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    @Transactional
    public void t5(){

    }
}
