package com.cranberries.elasticsearch.service.impl;

import com.cranberries.book.api.BookApi;
import com.cranberries.elasticsearch.entity.Book;
import com.cranberries.elasticsearch.repository.BookSearchRepository;
import com.cranberries.elasticsearch.service.BookSearchService;
import com.cranberries.book.dto.request.BookDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
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

    @Autowired
    private BookApi bookApi;

    @Override
    public void save(List<Book> books) {
        if (CollectionUtils.isEmpty(books)) {
            books = new ArrayList<>();
            List<BookDTO> bookDTOList = this.bookApi.queryAll();
            if (CollectionUtils.isNotEmpty(bookDTOList)) {
                for (BookDTO item : bookDTOList) {
                    if (item != null) {
                        Book book = new Book();
                        BeanUtils.copyProperties(item, book);
                        books.add(book);
                    }
                }
            }
        }
        this.bookSearchRepository.saveAll(books);
    }

    @Override
    public List<Book> query(Map<String, Object> request) {
        Iterable<Book> books = bookSearchRepository.findAll();
        List<Book> bookList = new ArrayList<>();
        books.forEach(item -> {
            Book book = new Book();
            BeanUtils.copyProperties(item, book);
            bookList.add(book);
        });
        return bookList;
    }

}
