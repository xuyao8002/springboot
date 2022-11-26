package com.xuyao.springboot.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xuyao
 * @since 2022-07-17
 */
@Data
@TableName("article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 作者
     */
    private String author;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 文章id
     */
    @TableField("article_id")
    private Long articleId;

    /**
     * 原始链接
     */
    @TableField("origin_url")
    private String originUrl;

    /**
     * 创建时间
     */
//    @TableField("create_time")
//    private Date createTime;

    /**
     * 更新时间
     */
//    @TableField("update_time")
//    private Date updateTime;

    /**
     * 删除标识，0未删除，1已删除
     */
//    private Integer deleted;

}
