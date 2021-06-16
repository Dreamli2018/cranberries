package com.cranberries.elasticsearch.repository;

import com.cranberries.elasticsearch.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author ：Dream Li
 * @version ：1.0.0
 * @program ：cranberries
 * @date ：Created in 2021/06/16 14:53
 * @description ：书籍搜索
 */
public interface BookSearchRepository extends ElasticsearchRepository<Book, Long> {

}
