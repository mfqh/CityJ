package com.whut.cityj.service;

import com.whut.cityj.bean.User;

public interface UserService {

    public User seekUser(String name, String password);

    public Boolean judgeUser(String name);

    public Boolean insUser(User user);
}
