package com.cranberries.book.api;

import com.cranberries.book.dto.request.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author ：Dream Li
 * @version ：1.0.0
 * @program ：cranberries
 * @date ：Created in 2021/06/18 10:47
 * @description ：书籍api
 */

@FeignClient(value = "book")
public interface BookApi {

    @GetMapping("/book/query")
    BookDTO query(Integer id);

    @GetMapping("/book/queryAll")
    List<BookDTO> queryAll();

}
