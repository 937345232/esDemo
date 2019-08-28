package com.example.demo.Reposity;

import com.example.demo.pojoEs.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author jzx
 * @Description
 * @Date: 2018-09-17 11:03
 */

public interface  BookRepository extends ElasticsearchRepository<Book,Long> {

}
