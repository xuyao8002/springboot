package com.xuyao.springboot.controller;


import com.xuyao.springboot.bean.po.Result;
import com.xuyao.springboot.bean.po.User;
import com.xuyao.springboot.bean.vo.UserVO;
import com.xuyao.springboot.config.WrapResponse;
import com.xuyao.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private IUserService userService;

    @PostMapping("/add")
    public Object add(@RequestBody User user){
        User loggedInUser = userService.getCurrentUser();
        userService.saveUser(user, loggedInUser);
        return Result.success("添加用户成功", user.getId());
    }

    @GetMapping("/getDetail")
    @WrapResponse
    public UserVO getDetail(User user){
        return userService.getDetail(user);
    }

}
