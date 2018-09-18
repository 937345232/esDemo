package com.example.demo.controller;

import com.example.demo.pojoEs.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jzx
 * @Description
 * @Date: 2018-09-17 16:08
 */
@RestController
public class MyEsController {

    @Autowired
    BookService bookService;

    @PostMapping("add")
    public Book add(Book book) {
        return bookService.add(book);
    }

    @PostMapping
    public void delete(Book book) {
        bookService.delete(book);
    }

    @GetMapping("findOne")
    public Book findOne(Long id) {
       return   bookService.findOne(id);

    }

    @GetMapping("findAll")
    public Iterable<Book> findAll() {

        return bookService.findAll();
    }

    @GetMapping("findByAuthor")
    public Page<Book> findByAuthor(String author) {

        return bookService.findByAuthor(author,1,100);
    }

    @GetMapping("findByTitle")
    public Page<Book> findByTitle(String title) {
        return bookService.findByTitle( title,  1,  100);

    }

    @GetMapping("findByText")
    public Page<Book> findByText(String text) {
        return bookService.findByText( text,  1,  100);
    }

    @GetMapping("findByPrice")
    public List<Book> findByPrice(int maxPrice, int minPrice) {
     return    bookService.findByPrice( maxPrice,  minPrice,  0,  100);
    }



    @GetMapping("phraseQuery")
    public List<Book> matchPhraseQuery(String content) {
        return    bookService.matchPhraseQuery( content);
    }

}
