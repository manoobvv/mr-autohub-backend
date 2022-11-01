package com.trackvehicle.magicrabbit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trackvehicle.magicrabbit.domain.Books;

public interface BooksRepository extends JpaRepository<Books, Integer> {

}
