package com.cranberries.book.service;

import com.cranberries.book.dto.request.BookDTO;

/**
 * @author ：Dream Li
 * @version ：1.0.0
 * @program ：cranberries
 * @date ：Created in 2021/06/18 10:40
 * @description ：书籍
 */
public interface BookService {
    void save(BookDTO bookDTO);

    void update(BookDTO bookDTO);

    void delete(Integer id);


}
