package xyz.yimb.kesheweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import xyz.yimb.kesheweb.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student getByAccountAndPassword(String account,String password);

    Student getByAccount(String account);

    @Modifying
    @Query(value = "update t_student set password=?1 where sid=?2",nativeQuery = true)
    int updatePwd(String password,Integer sid);

    @Modifying
    @Query(value = "update t_student set cid=?2 where sid=?1" ,nativeQuery = true)
    int updateCid(Integer sid, Integer cid);


    @Modifying
    @Query(value = "update t_student set mid=?1 where sid=?2" ,nativeQuery = true)
    int updateMidBySid(Integer mid, Integer sid);

    @Modifying
    @Query(value = "update t_student set wish=?1 where sid=?2" ,nativeQuery = true)
    int updateWish(String wish, Integer sid);

    @Modifying
    @Query(value = "update t_student set `name`=?1,phone=?2 where sid=?3" ,nativeQuery = true)
    int updNameAndPhone(String name, String phone, Integer sid);


}
