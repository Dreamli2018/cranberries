package com.cranberries.elasticsearch.service.impl;

import com.cranberries.elasticsearch.entity.Book;
import com.cranberries.elasticsearch.service.BookSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    @Override
    public void save(List<Book> books) {



    }

    @Override
    public List<Book> query(Map<String, Object> request) {
        return null;
    }
}
