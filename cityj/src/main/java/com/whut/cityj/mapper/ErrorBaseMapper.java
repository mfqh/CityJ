package com.whut.cityj.mapper;

import com.whut.cityj.bean.ErrorBase;
import com.whut.cityj.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErrorBaseMapper {

    //返回所有错题
    public List<ErrorBase> selAllError(User user);

    //从错题库中移除错题
    public Boolean delErrorQuestion(ErrorBase errorBase);

}
