package xyz.yimb.kesheweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.yimb.kesheweb.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    public User getByAccountAndPassword(String account,String password);
}
