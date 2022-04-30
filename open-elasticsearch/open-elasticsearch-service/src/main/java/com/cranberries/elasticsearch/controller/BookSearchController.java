package com.cranberries.elasticsearch.controller;

import com.alibaba.fastjson.JSONObject;
import com.cranberries.elasticsearch.entity.Book;
import com.cranberries.elasticsearch.service.BookSearchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private BookSearchService bookSearchService;

    @PostMapping("/save")
    @ApiOperation("新增书籍")
    public void save(@RequestParam(value = "request") String request){
        List<Book> books = JSONObject.parseArray(request, Book.class);
        bookSearchService.save(books);
    }

    @ApiOperation("查询所有书籍")
    @GetMapping("/query")
    public List<Book> query(@RequestParam(value = "request") String request){
        Map map = JSONObject.parseObject(request, Map.class);
        return bookSearchService.query(map);
    }

    @ApiOperation("条件搜索书籍")
    @PostMapping("/searchByCondition")
    public List<Book> searchByCondition(@RequestBody Book book){

        return bookSearchService.searchByCondition(book);
    }

}
