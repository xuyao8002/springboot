package com.xuyao.springboot.bean.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

@Getter
@Setter
public class ValidDTO {

    @NotBlank(message = "code必填")
    private String code;

    @NotNull(message = "id必填", groups = Update.class)
    private Long id;

    @NotBlank(message = "name必填", groups = {Insert.class, Update.class})
    private String name;

    public interface Insert extends Default {};
    public interface Update extends Default {};

}
