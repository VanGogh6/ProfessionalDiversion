package xyz.yimb.kesheweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.yimb.kesheweb.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    public Student getByAccountAndPassword(String account,String password);

}
