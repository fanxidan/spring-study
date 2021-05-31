package com.kuang.mapper;

import com.kuang.pojo.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserMapperImpl2 implements UserMapper{
    private SqlSession sqlSession;

    public List<User> selectUser(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        return userMapper.selectUser();
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
}
