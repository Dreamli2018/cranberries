package com.cranberries.elasticsearch.mapper;

import com.cranberries.elasticsearch.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

/**
 * @author ：Dream Li
 * @version ：1.0.0
 * @program ：cranberries
 * @date ：Created in 2021/06/16 14:53
 * @description ：书籍搜索
 */
public interface BookSearchMapper extends ElasticsearchCrudRepository<Book, Long> {

}
