package com.cranberries.book.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：Dream Li
 * @version ：1.0.0
 * @program ：cranberries
 * @date ：Created in 2021/06/18 11:11
 * @description ：书籍
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    private static final long serialVersionUID = 4024206544596493952L;

    /**
     * 主键pk
     */
    private Integer id;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * 价格
     */
    private Double price;

    /**
     * 国家
     */
    private String country;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 图片链接
     */
    private String imageUrl;

    /**
     * 出版日期
     */
    private Date publicationDate;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建日期
     */
    private Date createdDate;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新日期
     */
    private Date updatedDate;

    /**
     * 是否删除，0-否，1-是
     */
    private String isDelete;

}
