package com.cranberries.book.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：Dream Li
 * @version ：1.0.0
 * @program ：cranberries
 * @date ：Created in 2021/06/18 10:31
 * @description ：书籍
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO implements Serializable {

    private static final long serialVersionUID = 743474623973287952L;

    @ApiModelProperty(value = "主键-pk", example = "0000001")
    private Integer id;

    @ApiModelProperty(value = "书名", example = "围城", required = true)
    private String name;

    @ApiModelProperty(value = "作者", example = "钱钟书", required = true)
    private String author;

    @ApiModelProperty(value = "价格", example = "17.88", required = true)
    private Double price;

    @ApiModelProperty(value = "国家", example = "中国", required = true)
    private String country;

    @ApiModelProperty(value = "类型", example = "1-文学、2-计算机", required = true)
    private Integer type;

    @ApiModelProperty(value = "出版日期", example = "2020-06-18", required = true)
    private Date publicationDate;

    /**
     * 创建日期
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

}
