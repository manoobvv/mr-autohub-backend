package com.trackvehicle.magicrabbit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trackvehicle.magicrabbit.domain.Books;
import com.trackvehicle.magicrabbit.services.BooksService;

// mark class as Controller
@RestController

public class BooksController {

    // autowire the BooksService class
    @Autowired
    BooksService booksService;

    // creating a get mapping that retrieves all the books detail from the database
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/book")
    public List<Books> getAllBooks() {
        return this.booksService.getAllBooks();
    }

    // creating a get mapping that retrieves the detail of a specific book
    @GetMapping("/book/{bookid}")
    private Books getBooks(@PathVariable("bookid") final int bookid) {
        return this.booksService.getBooksById(bookid);
    }

    // creating a delete mapping that deletes a specified book
    @DeleteMapping("/book/{bookid}")
    private void deleteBook(@PathVariable("bookid") final int bookid) {
        this.booksService.delete(bookid);
    }

    // creating post mapping that post the book detail in the database
    @PostMapping("/books")
    private int saveBook(@RequestBody final Books books) {
        this.booksService.saveOrUpdate(books);
        return books.getBookid();
    }

    // creating put mapping that updates the book detail
    @PutMapping("/books")
    private Books update(@RequestBody final Books books) {
        this.booksService.saveOrUpdate(books);
        return books;
    }
}
