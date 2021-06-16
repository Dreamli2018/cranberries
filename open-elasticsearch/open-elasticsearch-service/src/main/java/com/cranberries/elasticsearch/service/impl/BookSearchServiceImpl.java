package com.cranberries.elasticsearch.service.impl;

import com.cranberries.elasticsearch.entity.Book;
import com.cranberries.elasticsearch.repository.BookSearchRepository;
import com.cranberries.elasticsearch.service.BookSearchService;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：Dream Li
 * @version ：1.0.0
 * @program ：cranberries
 * @date ：Created in 2021/06/15 18:35
 * @description ：书籍搜索
 */

@Service
@Slf4j
public class BookSearchServiceImpl implements BookSearchService {

    @Autowired
    private BookSearchRepository bookSearchRepository;

    @Override
    public void save(List<Book> books) {

        this.bookSearchRepository.saveAll(books);
    }

    @Override
    public List<Book> query(Map<String, Object> request) {
        Iterable<Book> books = bookSearchRepository.findAll();
        List<Book> bookList = new ArrayList<>();
        books.forEach(item->{
            Book book = new Book();
            BeanUtils.copyProperties(item, book);
            bookList.add(book);
        });
        return bookList;
    }

}
