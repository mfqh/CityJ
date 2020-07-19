package com.whut.cityj.service.impl;

import com.whut.cityj.bean.ErrorBase;
import com.whut.cityj.bean.User;
import com.whut.cityj.mapper.ErrorBaseMapper;
import com.whut.cityj.service.ErrorBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ErrorBaseServiceImpl implements ErrorBaseService {

    @Autowired
    ErrorBaseMapper errorBaseMapper;

    @Transactional(readOnly = true)
    @Override
    public List<ErrorBase> getAllError(User user) {
        return errorBaseMapper.selAllError(user);
    }

    @Override
    public Boolean removeError(ErrorBase errorBase) {
        return errorBaseMapper.delErrorQuestion(errorBase);
    }
}
