package xyz.yimb.kesheweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import xyz.yimb.kesheweb.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {

    User getByAccountAndPassword(String account,String password);

    @Modifying
    @Query(value = "update `t_user` set password=?1 where uid=?2" ,nativeQuery = true)
    int updPwd(String password, Integer uid);
}
