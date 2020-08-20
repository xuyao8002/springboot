package com.xuyao.springboot.bean.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class ValidDTO {

    @NotBlank(message = "code必填")
    private String code;

    @NotNull(message = "id必填", groups = Update.class)
    private Long id;

    @NotBlank(message = "name必填", groups = {Insert.class, Update.class})
    private String name;
    //post请求日期格式化
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //get请求日期格式化
    // @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    // private LocalDateTime date;
    public interface Insert extends Default {};
    public interface Update extends Default {};

}
