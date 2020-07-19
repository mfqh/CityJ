package com.whut.cityj.service;

import com.whut.cityj.bean.ErrorBase;
import com.whut.cityj.bean.User;

import java.util.List;

public interface ErrorBaseService {

    public List<ErrorBase> getAllError(User user);

    public Boolean removeError(ErrorBase errorBase);

}
