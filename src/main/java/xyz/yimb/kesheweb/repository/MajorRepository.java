package xyz.yimb.kesheweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import xyz.yimb.kesheweb.entity.Major;

public interface MajorRepository extends JpaRepository<Major,Integer> {

    @Modifying
    @Query(value = "insert into t_major(name,number,cid) values (?1,?2,?3)" ,nativeQuery = true)
    int insertMajor(String name, String number, String cid);

    @Modifying
    @Query(value = "update t_major set `number`=?1 where mid=?2",nativeQuery = true)
    int updateNumber(Integer number, Integer mid);

    @Modifying
    @Query(value = "update t_major set `nownumber`=?1 where mid=?2",nativeQuery = true)
    int updateMjorNowNmber(Integer nownumber, Integer mid);
}
