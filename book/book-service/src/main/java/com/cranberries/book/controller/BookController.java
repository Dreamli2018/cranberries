package com.cranberries.book.controller;

import com.cranberries.book.api.BookApi;
import com.cranberries.book.service.BookService;
import com.cranberries.book.dto.request.BookDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ：Dream Li
 * @version ：1.0.0
 * @program ：cranberries
 * @date ：Created in 2021/06/18 10:38
 * @description ：书籍
 */

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookApi bookApi;

    @PostMapping("/save")
    @ApiOperation("新增书籍")
    public void save(@Valid @RequestBody BookDTO bookDTO){

        this.bookService.save(bookDTO);

    }

    @PostMapping("/update")
    @ApiOperation("更新书籍")
    public void update(@Valid @RequestBody BookDTO bookDTO){

        this.bookService.update(bookDTO);

    }

    @PostMapping("/delete")
    @ApiOperation("删除书籍")
    public void delete(Integer id){

        this.bookService.delete(id);

    }


    public BookDTO query(Integer id){

        return this.bookApi.query(id);

    }

    public List<BookDTO> queryAll(){

       return this.bookApi.queryAll();

    }
}
