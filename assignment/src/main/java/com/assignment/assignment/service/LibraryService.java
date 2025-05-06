package com.assignment.assignment.service;

import com.assignment.assignment.DTO.ResponseDTO.BookResponseDTO;
import com.assignment.assignment.DTO.ResponseDTO.BorrowResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LibraryService {


    public List<BookResponseDTO> getAvailableBooks();

    public List<BookResponseDTO> searchBooks(String author, Integer year);

    public BorrowResponseDTO borrowBook(String email, Integer bookId);

    public BorrowResponseDTO returnBook(String email, Integer bookId);

    public List<String> viewHistory(String email);
}
