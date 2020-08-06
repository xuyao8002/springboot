package com.xuyao.springboot.bean.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NestedDTO {

    @NotNull(message = "hobby不能为空")
    private String hobby;

}
