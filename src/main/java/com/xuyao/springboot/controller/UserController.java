package com.xuyao.springboot.controller;


import com.xuyao.springboot.bean.po.Result;
import com.xuyao.springboot.bean.po.User;
import com.xuyao.springboot.bean.vo.UserDTO;
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
    public Object getDetail(User user){
        UserDTO detail = userService.getDetail(user);
        return Result.success("获取用户详情成功", detail);

    }

}
