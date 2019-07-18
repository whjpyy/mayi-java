package com.mayi.mayispringboot.mapper;

import com.mayi.mayispringboot.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "baseCache")
public interface UserMapper {

    @Cacheable
    @Select("select * from users where name = #{name}")
    User findByName(@Param("name") String name);
}
