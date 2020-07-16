package com.whut.cityj.service.impl;

import com.whut.cityj.bean.Manager;
import com.whut.cityj.mapper.ManagerMapper;
import com.whut.cityj.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    public ManagerMapper managerMapper;

    @Override
    public Manager seekManager(String name, String password) {
        return managerMapper.selManager(name, password);
    }
}
