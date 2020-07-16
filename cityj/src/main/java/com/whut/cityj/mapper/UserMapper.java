package com.whut.cityj.mapper;

import com.whut.cityj.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    //登陆
    @Select("select * from user where name = #{param1} and  password = #{param2}")
    public User selUser(String name, String password);

    //注册查询是否存在
    @Select("select * from user where name = #{param}")
    public User selUserExist(String name);

    //注册
    @Insert("insert into user(name,password) values(#{name}, #{password})")
    public Boolean insUser(User user);

}
