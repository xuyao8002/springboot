package com.xuyao.springboot.bean.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@Setter
public class User {
    private Long id;

    private String name;

    private String username;

    private String password;

    private Integer gender;

    private Date createDate;

    private String phone;

    private String email;

    private String address;

}