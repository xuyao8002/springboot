package com.xuyao.springboot.bean.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserDTO {
    private Long id;

    private String name;

    private String username;

    private Integer gender;

    private Date createDate;

    private String phone;

    private String email;

    private String address;

}