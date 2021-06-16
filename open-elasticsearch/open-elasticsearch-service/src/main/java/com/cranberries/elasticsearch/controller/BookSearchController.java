package com.cranberries.elasticsearch.controller;

import com.cranberries.elasticsearch.entity.Book;
import com.cranberries.elasticsearch.service.BookSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author ：Dream Li
 * @version ：1.0.0
 * @program ：cranberries
 * @date ：Created in 2021/06/15 18:05
 * @description ：书籍搜索
 */
@RestController
@RequestMapping("/search/book")
public class BookSearchController {

    private BookSearchService bookSearchService;

    @ApiOperation("新增书籍")
    public void save(@Valid List<Book> books){
        bookSearchService.save(books);

    }


    @ApiOperation("根据条件查询书籍")
    public List<Book> query(@Valid Map<String, Object> request){
       return bookSearchService.query(request);
    }

}
