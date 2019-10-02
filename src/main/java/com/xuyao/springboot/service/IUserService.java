package com.xuyao.springboot.service;


import com.xuyao.springboot.bean.po.User;
import com.xuyao.springboot.bean.vo.UserVO;

public interface IUserService {

    int saveUser(User saveUser, User loggedInUser);

    UserVO getDetail(User user);

    User getCurrentUser();

    User selectOne(User user);

}
