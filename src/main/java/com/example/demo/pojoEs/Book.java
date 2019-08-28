package com.example.demo.pojoEs;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldIndex;


import java.io.Serializable;

/**
 * @author jzx
 * @Description
 * @Date: 2018-09-17 10:48
 */
@Document(indexName = "jzx", type = "books")
public class Book implements Serializable {

    @Id
    private Long id;
    @Field(analyzer="ik" ,store=true,searchAnalyzer="ik")
    private String title;

    @Field(analyzer="ik" ,store=true,searchAnalyzer="ik")
    private String author;

    @Field(analyzer="ik" ,store=true,searchAnalyzer="ik")
    private String desc;

    private int price;

    public Book(Long id, String title, String author, String desc, int price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.desc = desc;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book() {
    }

    public Book(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                '}';
    }
}
