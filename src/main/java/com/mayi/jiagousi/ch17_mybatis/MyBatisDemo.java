package com.mayi.jiagousi.ch17_mybatis;

import com.mayi.jiagousi.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class MyBatisDemo {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis.xml";
        // 读取配置文件
        Reader reader = Resources.getResourceAsReader(resource);
        // 获取会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();
        // 查询
        String sql = "com.mayi.jiagousi.ch17_mybatis.UserTableMapper.getById";
        // 调用api查询
        List<User> list = session.selectList(sql, 1);
        for (User user: list){
            System.out.println(user);
        }

    }
}
