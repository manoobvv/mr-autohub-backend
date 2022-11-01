package com.trackvehicle.magicrabbit.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
// defining class name as Table name
@Table
public class Books {

    // Defining book id as primary key
    @Id
    @Column
    private int bookid;

    @Column
    private String bookname;

    @Column
    private String author;

    @Column
    private int price;

    public int getBookid() {
        return this.bookid;
    }

    public void setBookid(final int bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return this.bookname;
    }

    public void setBookname(final String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }
}
