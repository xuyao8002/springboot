package com.xuyao.springboot.dao;

import com.xuyao.springboot.bean.po.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    void batchInsert(List<User>list);

    int batchUpdate(List<User> list);
}