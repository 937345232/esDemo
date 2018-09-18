package com.example.demo;


import com.example.demo.pojoEs.Book;
import com.example.demo.service.BookService;
import org.elasticsearch.client.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsdemoApplicationTests {



	@Autowired
	private ElasticsearchOperations es;

	@Autowired
	BookService bookService;

	@Test
	public void contextLoads() {

		for(int i=1; i<=100; i++){
			Book book = new Book(Long.valueOf(i), "亚马逊"+i, "wudi"+i, "这是一本测试书记"+i, (10+i));
			bookService.add(book);
			System.out.println("book = " + book);
		}
	}

@Test
	public  void contextTest(){
		System.out.println("--ElasticSearch-->");
		Client client = es.getClient();
		Map<String, String> asMap = client.settings().getAsMap();
		asMap.forEach((k, v) -> {
			System.out.println(k + " = " + v);
		});
		System.out.println("<--ElasticSearch--");
	}


	//查询所有
	@Test
	public  void seachTest(){
		Iterable<Book> all = bookService.findAll();
		Iterator<Book> iterator = all.iterator();
		List<Book> books = copyIterator(iterator);
		System.out.println("总记录数:" +books.size());
		for (Book book:books){
			System.out.println("book = " + book);
		}
	}


	@Test
	public  void seachTest02(){
		Page<Book> byTitle = bookService.findByTitle("亚马逊", 1, 10);
		int totalPages = byTitle.getTotalPages();
		System.out.println("totalPages = " + totalPages);
		Iterator<Book> iterator = byTitle.iterator();
		byTitle.forEach(book -> System.out.println("book = " + book));

	}

	@Test
	public  void seachTest03(){
		Page<Book> byTitle = bookService.findByText("wudi", 1, 10);
		int totalPages = byTitle.getTotalPages();
		System.out.println("totalPages = " + totalPages);
		Iterator<Book> iterator = byTitle.iterator();
		byTitle.forEach(book -> System.out.println("book = " + book));

	}






	public static <T> List<T> copyIterator(Iterator<T> iter) {
		List<T> copy = new ArrayList<T>();
		while (iter.hasNext()) {
			copy.add(iter.next());
		}
		return copy;
	}


}
