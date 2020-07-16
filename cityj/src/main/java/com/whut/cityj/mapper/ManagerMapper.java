package com.whut.cityj.mapper;


import com.whut.cityj.bean.Manager;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerMapper {

    //查询管理员
    @Select("select * from manager where name = #{param1} and password = #{param2}")
    public Manager selManager(String name, String password);


}
