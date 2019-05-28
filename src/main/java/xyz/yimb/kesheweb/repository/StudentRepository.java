package xyz.yimb.kesheweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.yimb.kesheweb.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    public Student getByAccountAndPassword(String account,String password);

}
