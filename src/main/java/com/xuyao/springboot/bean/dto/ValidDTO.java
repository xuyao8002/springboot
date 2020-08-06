package com.xuyao.springboot.bean.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;

@Getter
@Setter
public class ValidDTO {

    @NotBlank(message = "code必填")
    private String code;

    @NotNull(message = "id必填", groups = Update.class)
    private Long id;

    @NotBlank(message = "name必填", groups = {Insert.class, Update.class})
    private String name;

    //@Valid嵌套校验
    @Valid
    @NotNull(message = "others不能为空")
    private List<NestedDTO> others;

    public interface Insert extends Default {};
    public interface Update extends Default {};

}
