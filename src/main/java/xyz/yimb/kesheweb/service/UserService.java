package xyz.yimb.kesheweb.service;

import xyz.yimb.kesheweb.entity.User;

import java.util.List;

public interface UserService {

    public User getByAccountAndPassword(String account,String password);

    void save(User user);

    List<User> getAll();
}
