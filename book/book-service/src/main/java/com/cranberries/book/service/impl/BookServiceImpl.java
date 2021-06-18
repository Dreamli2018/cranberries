package com.cranberries.book.service.impl;

import com.cranberries.book.api.BookApi;
import com.cranberries.book.mapper.BookMapper;
import com.cranberries.book.service.BookService;
import com.cranberries.book.dto.request.BookDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：Dream Li
 * @version ：1.0.0
 * @program ：cranberries
 * @date ：Created in 2021/06/18 10:41
 * @description ：书籍
 */
@Service
@Slf4j
@RestController("/book")
public class BookServiceImpl implements BookService , BookApi {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public void save(BookDTO bookDTO) {

    }

    @Override
    public void update(BookDTO bookDTO) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public BookDTO query(Integer id) {
        return null;
    }

    @Override
    public List<BookDTO> queryAll() {
        return bookMapper.queryAll();
    }
}
