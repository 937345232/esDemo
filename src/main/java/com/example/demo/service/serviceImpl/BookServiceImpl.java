package com.example.demo.service.serviceImpl;

import com.example.demo.Reposity.BookRepository;
import com.example.demo.pojoEs.Book;
import com.example.demo.service.BookService;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author jzx
 * @Description
 * @Date: 2018-09-17 11:25
 */
@Service
public class BookServiceImpl implements BookService {



    @Autowired
    BookRepository bookReposity;


    @Override
    public Book add(Book book) {
        return bookReposity.save(book);
    }

    @Override
    public void delete(Book book) {
        bookReposity.delete(book);
    }

    @Override
    public Book findOne(Long id) {
        Optional<Book> byId = bookReposity.findById(id);
       return byId.get();
    }

    @Override
    public Iterable<Book> findAll() {

        return bookReposity.findAll();
    }

    @Override
    public Page<Book> findByAuthor(String author, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        return bookReposity.search(QueryBuilders.matchQuery("author", author), pageRequest);
    }

    @Override
    public Page<Book> findByTitle(String title, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        return bookReposity.search(QueryBuilders.matchQuery("title", title), pageRequest);

    }

    @Override
    public Page<Book> findByText(String text, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        return bookReposity.search(QueryBuilders.multiMatchQuery(text, "author", "title"), pageRequest);
    }

    @Override
    public List<Book> findByPrice(int maxPrice, int minPrice, int page, int size) {
        RangeQueryBuilder price = QueryBuilders.rangeQuery("price").from(minPrice).to(maxPrice);

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "price"));

        Iterable<Book> search = bookReposity.search(price,pageRequest);
        return copyIterator(search.iterator());
    }

    @Override
    public List<Book> matchPhraseQuery(String content) {
        MatchQueryBuilder title = QueryBuilders.matchQuery("title", content);
        PageRequest pageRequest = PageRequest.of(1, 10, Sort.by(Sort.Direction.DESC, "price"));
        Iterable<Book> search = bookReposity.search(title,pageRequest);
        return copyIterator(search.iterator());
    }




    public static <T> List<T> copyIterator(Iterator<T> iter) {
        List<T> copy = new ArrayList<T>();
        while (iter.hasNext()) {
            copy.add(iter.next());
        }
        return copy;
    }

}
