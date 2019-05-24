package xyz.yimb.kesheweb.service;

import xyz.yimb.kesheweb.entity.User;

public interface UserService {

    public User getByAccountAndPassword(String account,String password);

    void save(User user);

}
