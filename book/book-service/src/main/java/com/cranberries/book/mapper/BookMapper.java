package com.cranberries.book.mapper;

import com.cranberries.book.dto.request.BookDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ：Dream Li
 * @version ：1.0.0
 * @program ：cranberries
 * @date ：Created in 2021/06/18 10:42
 * @description ：书籍
 */

@Mapper
public interface BookMapper {

    @Select("select * from book")
    List<BookDTO> queryAll();
}
