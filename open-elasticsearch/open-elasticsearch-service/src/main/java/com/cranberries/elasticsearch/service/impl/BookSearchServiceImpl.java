package com.cranberries.elasticsearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.cranberries.book.api.BookApi;
import com.cranberries.book.dto.request.BookDTO;
import com.cranberries.elasticsearch.entity.Book;
import com.cranberries.elasticsearch.repository.BookSearchRepository;
import com.cranberries.elasticsearch.service.BookSearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
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
        log.info("要保存书籍列表:{}", JSON.toJSONString(books));
        try {
            this.bookSearchRepository.saveAll(books);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("保存书籍到es异常:{}", e.getMessage());
        }
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

    @Override
    public List<Book> searchByCondition(Book book) {
        // 条件过滤
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("type", book.getType()))
                .must(QueryBuilders.termQuery("author.keyword", book.getAuthor()))
                .must(QueryBuilders.rangeQuery("price").gte(20).lte(40));
        // 聚合
        TermsAggregationBuilder aggregationBuilder =
                AggregationBuilders.terms("by_author").field("author.keyword").size(20)
                .subAggregation(AggregationBuilders.sum("sum_price").field("price"))
                .order(BucketOrder.aggregation("sum_price", true));
        // 搜索条件
        NativeSearchQuery searchQuery = new NativeSearchQuery(queryBuilder);
        searchQuery.addAggregation(aggregationBuilder);
        // 执行搜索
        Iterable<Book> books = bookSearchRepository.search(searchQuery);
        List<Book> bookList = new ArrayList<>();
        books.forEach(item -> {
            Book book1 = new Book();
            BeanUtils.copyProperties(item, book1);
            bookList.add(book1);
        });
        return bookList;
    }

}
