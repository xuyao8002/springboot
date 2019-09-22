package com.xuyao.springboot.service;


import com.xuyao.springboot.bean.po.User;
import com.xuyao.springboot.bean.vo.UserDTO;

public interface IUserService {

    int saveUser(User saveUser, User loggedInUser);

    UserDTO getDetail(User user);

    User getCurrentUser();

    User selectOne(User user);

}
