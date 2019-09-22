package com.xuyao.springboot.service.impl;


import com.xuyao.springboot.bean.po.User;
import com.xuyao.springboot.bean.vo.UserDTO;
import com.xuyao.springboot.dao.IUserDao;
import com.xuyao.springboot.service.IUserService;
import com.xuyao.springboot.utils.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public int saveUser(User saveUser, User loggedInUser) {

        saveUser.setPassword(CommonUtils.encryPassowrd(saveUser.getUsername(), saveUser.getPassword()));

        saveUser.setCreateDate(new Date());

        return userDao.insertSelective(saveUser);
    }

    @Override
    public UserDTO getDetail(User user) {
        User source = selectOne(user);
        return CommonUtils.toDTO(source, UserDTO.class);
    }

    @Override
    public User getCurrentUser() {
        return (User) redisTemplate.opsForValue().get(CommonUtils.getTokenId(request));
    }

    @Override
    public User selectOne(User user) {
        return userDao.selectOne(user);
    }
}
