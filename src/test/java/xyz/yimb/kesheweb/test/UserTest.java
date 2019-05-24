package xyz.yimb.kesheweb.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.yimb.kesheweb.entity.User;
import xyz.yimb.kesheweb.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private UserService userServiceImpl;

    @Test
    public void getU(){
       User user = userServiceImpl.getByAccountAndPassword("admin", "1");
       System.out.println(user);
    }
}
