package xyz.yimb.kesheweb.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.yimb.kesheweb.entity.College;
import xyz.yimb.kesheweb.entity.Major;
import xyz.yimb.kesheweb.repository.CollegeRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollegeTest {
    @Autowired
    private CollegeRepository collegeRepository;


    //
    @Test
    @Transactional
    public void t1(){
        College college = collegeRepository.getOne( 1);
        System.out.println(college);
        List<Major> majorList = college.getMajors();
        for (int i = 0; i <majorList.size() ; i++) {
            System.out.println(majorList.get(i));
        }
    }
}
