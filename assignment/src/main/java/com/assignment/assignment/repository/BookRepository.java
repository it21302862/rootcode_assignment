package com.assignment.assignment.repository;


import com.assignment.assignment.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByPublishedYear(int year);
    List<Book> findByAvailableCopiesGreaterThan(int minCopies);
}
