package com.trackvehicle.magicrabbit.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackvehicle.magicrabbit.domain.Books;
import com.trackvehicle.magicrabbit.repository.BooksRepository;

// defining the business logic
@Service
public class BooksService {

    @Autowired
    BooksRepository booksRepository;

    // getting all books record by using the method findaAll() of CrudRepository
    public List<Books> getAllBooks() {
        final List<Books> books = new ArrayList<Books>();
        this.booksRepository.findAll().forEach(books1 -> books.add(books1));
        return books;
    }

    // getting a specific record by using the method findById() of CrudRepository
    public Books getBooksById(final int id) {
        return this.booksRepository.findById(id).get();
    }

    // saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(final Books books) {
        this.booksRepository.save(books);
    }

    // deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(final int id) {
        this.booksRepository.deleteById(id);
    }

    // updating a record
    public void update(final Books books, final int bookid) {
        this.booksRepository.save(books);
    }
}
