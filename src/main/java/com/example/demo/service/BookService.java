package com.example.demo.service;

import com.example.demo.pojoEs.Book;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author jzx
 * @Description
 * @Date: 2018-09-17 11:36
 */

public interface BookService {

    Book add(Book book);

    void delete(Book book);

    Book findOne(Long id);

    Iterable<Book> findAll();

    Page<Book> findByAuthor(String author, int page, int size);

    Page<Book> findByTitle(String title, int page, int size);

    Page<Book> findByText(String text,int page, int size);

    List<Book> findByPrice(int maxPrice, int minPrice, int page, int size);


    List<Book> matchPhraseQuery(String content);
}
