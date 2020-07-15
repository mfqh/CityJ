package com.whut.cityj.mapper;

import com.whut.cityj.bean.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Select("select * from user where name = #{param1} and  password = #{param2}")
    public User selUser(String name, String password);

}
