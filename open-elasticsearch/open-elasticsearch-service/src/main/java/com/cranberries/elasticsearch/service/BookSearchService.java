package com.cranberries.elasticsearch.service;

import com.cranberries.elasticsearch.entity.Book;

import java.util.List;
import java.util.Map;

/**
 * @author ：Dream Li
 * @version ：1.0.0
 * @program ：cranberries
 * @date ：Created in 2021/06/15 18:35
 * @description ：书籍搜索
 */
public interface BookSearchService {

    void save(List<Book> books);

    List<Book> query(Map<String, Object> request);

    List<Book> searchByCondition(Book book);
}
