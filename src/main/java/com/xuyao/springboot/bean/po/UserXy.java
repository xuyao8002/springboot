package com.xuyao.springboot.bean.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "xuyao")
@Getter
@Setter
@ToString
public class UserXy implements Serializable {

    @Id
    private String id;

    private String name;

    private String description;

    private Integer age;

    private Date createDate;

    private Date updateDate;

}
