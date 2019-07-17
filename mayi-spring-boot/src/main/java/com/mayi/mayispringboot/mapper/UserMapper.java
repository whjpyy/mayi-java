package com.mayi.mayispringboot.mapper;

import com.mayi.mayispringboot.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from user where name = #{name}")
    User findByName(@Param("name") String name);
}
