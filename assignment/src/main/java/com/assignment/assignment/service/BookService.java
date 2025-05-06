package com.assignment.assignment.service;

import com.assignment.assignment.DTO.RequestDTO.BookRequestDTO;
import com.assignment.assignment.DTO.ResponseDTO.BookResponseDTO;
import com.assignment.assignment.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    public BookResponseDTO addBook(BookRequestDTO bookRequestDTO);

    public BookResponseDTO getBookById(Integer bookId);

    public Book getBook(Integer bookId);

    public List<BookResponseDTO> getAllBooks();

    public BookResponseDTO deleteBook(Integer bookId);

    public BookResponseDTO updateBook(Integer bookId, BookRequestDTO bookRequestDTO);
}
