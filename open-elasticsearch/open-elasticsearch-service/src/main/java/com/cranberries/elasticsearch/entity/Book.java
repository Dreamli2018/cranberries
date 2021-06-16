package com.cranberries.elasticsearch.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.lang.annotation.Native;

/**
 * @author ：Dream Li
 * @version ：
 * @program ：cranberries
 * @date ：Created in 2021/06/15 18:18
 * @description ：
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "index_book")
public class Book implements Serializable {

    @Field(name = "id")
    @ApiModelProperty(value = "主键-pk", example = "0000001")
    private Integer id;


    @Field(name = "name")
    @ApiModelProperty(value = "书名", example = "围城", required = true)
    private String name;

    @Field(name = "author")
    @ApiModelProperty(value = "作者", example = "钱钟书", required = true)
    private String author;

    @Field(name = "price")
    @ApiModelProperty(value = "价格", example = "17.88", required = true)
    private Double price;

    @Field(name = "country")
    @ApiModelProperty(value = "国家", example = "中国", required = true)
    private String country;

    @Field(name = "type")
    @ApiModelProperty(value = "类型", example = "1-文学、2-计算机", required = true)
    private Integer type;
}
