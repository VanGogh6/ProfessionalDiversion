package xyz.yimb.kesheweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.yimb.kesheweb.entity.User;
import xyz.yimb.kesheweb.repository.UserRepository;
import xyz.yimb.kesheweb.service.UserService;
import xyz.yimb.kesheweb.utils.WebUtils;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getByAccountAndPassword(String account, String password) {
        return userRepository.getByAccountAndPassword(account, WebUtils.getMD5(password));
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }


}
